package com.example.demo.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.paper.model.MCitation;
import com.example.demo.domain.paper.model.MPaper;
import com.example.demo.domain.service.PaperService;
import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.PaperDetailForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("home/")
@Slf4j
public class PaperDetailController {

	
		@Autowired
		private UserService userService;
	
		@Autowired
		private PaperService paperService;
		
		@Autowired
		private ModelMapper modelMapper;
	
		@GetMapping("detail/{paperId}")
		public String getPaper(@AuthenticationPrincipal org.springframework.security.core.userdetails.User loginUser, @ModelAttribute PaperDetailForm paperDetailForm, Model model, @PathVariable("paperId") BigInteger paperId) {
			//論文情報を取得
			MPaper paper = paperService.getPaper(paperId);
			
			//ログインユーザと異なるユーザの論文情報を参照しようとした場合
			MUser user = userService.getLoginUser(loginUser.getUsername());
			if(!paper.getUserSerial().equals(user.getUserSerial())) {
				//不正なアクセス
				return "/prohibited";
			}
			
			
			try {
				//論文情報をformに変換
				paperDetailForm = modelMapper.map(paper, PaperDetailForm.class);
				//論文詳細情報をmodelに格納
				model.addAttribute("paperDetailForm", paperDetailForm);
				
				
				//先行研究情報を取得
				List<MCitation> preCitation = paperService.getPreCitations(paperId);
				//modelに格納
				model.addAttribute("preCitations", preCitation);
				
				//後続研究情報を取得
				List<MCitation> subCitation = paperService.getSubCitations(paperId);
				//modelに格納
				model.addAttribute("subCitations", subCitation);
			} catch(Exception e) {
				log.error("論文詳細表示でエラー", e);
			}
			
			
			
			return "/home/detail";
		}
		
		
		@PostMapping(value = "/detail/{paperId}", params = "update")
		public String updatePaper(@AuthenticationPrincipal org.springframework.security.core.userdetails.User loginUser, @ModelAttribute PaperDetailForm paperDetailForm, Model model, @PathVariable("paperId") BigInteger paperId) {
			/* 修正画面へ移動 */
			//論文情報を取得
			MPaper paper = paperService.getPaper(paperId);
			
			//論文情報をformに変換
			paperDetailForm = modelMapper.map(paper, PaperDetailForm.class);
			
			
			try {
				//ログイン中ユーザの登録論文一覧を取得
				MUser user = userService.getLoginUser(loginUser.getUsername());
				List<MPaper> paperList = paperService.getPapers(user);
				model.addAttribute("paperList", paperList);
				
				//先行研究情報を取得
				List<MCitation> preCitation = paperService.getPreCitations(paperId);
				List<BigInteger> preCitationList = new ArrayList<>();
				for(MCitation c : preCitation) {
					preCitationList.add(c.getCitedPaperId());
				}
				paperDetailForm.setPreCitationList(preCitationList);
				
				//後続研究情報を取得
				List<MCitation> subCitation = paperService.getSubCitations(paperId);
				List<BigInteger> subCitationList = new ArrayList<>();
				for(MCitation c : subCitation) {
					subCitationList.add(c.getCitingPaperId());
				}
				paperDetailForm.setSubCitationList(subCitationList);
				
				//論文詳細情報をmodelに格納
				model.addAttribute("paperDetailForm", paperDetailForm);
			} catch (Exception e) {
				log.error("論文更新表示でエラー", e);
			}
			
			return "/home/paper/update";
		}
		
		@PostMapping(value = "/detail/{paperId}", params = "delete")
		public String deltePaper(@PathVariable("paperId") BigInteger paperId) {
			//削除処理
			try {
				paperService.deletePaper(paperId);
			} catch (Exception e) {
				log.error("論文削除でエラー", e);
			}
			
			
			return "/home/paper/delete";
		}
		
		
		@PostMapping("/detail/update")
		public String exeUpdatePaper(@AuthenticationPrincipal org.springframework.security.core.userdetails.User loginUser, @ModelAttribute @Validated PaperDetailForm form, BindingResult bindingResult, Model model) {
			
			//バリデーション
			if(bindingResult.hasErrors()) {
				// ログイン中ユーザの登録論文一覧を取得
				MUser user = userService.getLoginUser(loginUser.getUsername());
				List<MPaper> paperList = paperService.getPapers(user);
				model.addAttribute("paperList", paperList);
				//登録画面に戻る
				return "/home/paper/update";
			}
			
			try {
				paperService.updatePaperWithCitations(form);
			} catch(Exception e) {
				log.error("論文情報更新でエラー", e);
			}

			return "/home/paper/complete";
		}
}
