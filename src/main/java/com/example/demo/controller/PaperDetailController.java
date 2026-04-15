package com.example.demo.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.paper.model.MCitation;
import com.example.demo.domain.paper.model.MPaper;
import com.example.demo.domain.service.PaperService;
import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.PaperDetailForm;

@Controller
@RequestMapping("home/")
public class PaperDetailController {

	
		@Autowired
		private UserService userService;
	
		@Autowired
		private PaperService paperService;
		
		@Autowired
		private ModelMapper modelMapper;
	
		@GetMapping("detail/{paperId}")
		public String getPaper(PaperDetailForm paperDetailForm, Model model, @PathVariable("paperId") BigInteger paperId) {
			//論文情報を取得
			MPaper paper = paperService.getPaper(paperId);
			
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
			
			
			return "/home/detail";
		}
		
		@PostMapping(value = "/detail/{paperId}", params = "update")
		public String updatePaper(@AuthenticationPrincipal org.springframework.security.core.userdetails.User loginUser, PaperDetailForm paperDetailForm, Model model, @PathVariable("paperId") BigInteger paperId) {
			/* 修正画面へ移動 */
			//論文情報を取得
			MPaper paper = paperService.getPaper(paperId);
			
			//論文情報をformに変換
			paperDetailForm = modelMapper.map(paper, PaperDetailForm.class);
			
			
			
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
			
			
			return "/home/paper/update";
		}
		
		@PostMapping(value = "/detail/{paperId}", params = "delete")
		public String deltePaper(@PathVariable("paperId") BigInteger paperId) {
			//削除処理
			paperService.deletePaper(paperId);
			
			return "/home/paper/delete";
		}
}
