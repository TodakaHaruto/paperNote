package com.example.demo.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.paper.model.MPaper;
import com.example.demo.domain.service.PaperService;
import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.PaperDetailForm;
import com.example.demo.form.RegisterPaperForm;

@Controller
@RequestMapping("/home")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PaperService paperService;
	
	//論文登録画面を表示
	@GetMapping("/register")
	public String getRegister(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User loginUser) {
		//ログイン中ユーザの登録論文一覧を取得
		MUser user = userService.getLoginUser(loginUser.getUsername());
		List<MPaper> paperList = paperService.getPapers(user);
		model.addAttribute("paperList", paperList);
		
		return "/home/register";
	}
	
	@PostMapping("/register")
	public String postRegister(@AuthenticationPrincipal org.springframework.security.core.userdetails.User loginUser, Model model, @ModelAttribute RegisterPaperForm form) {
		//ユーザ情報を取得
		MUser user = userService.getLoginUser(loginUser.getUsername());
		/* 論文登録処理 */
		PaperDetailForm paperForm = new PaperDetailForm();
		//formに情報を格納
		paperForm.setUserSerial(user.getUserSerial());
		paperForm.setTitle(form.getTitle());
		paperForm.setReadDate(form.getReadDate());
		paperForm.setPaperUrl(form.getPaperUrl());
		paperForm.setLearningNote(form.getLearningNote());
		//登録処理を実行
		paperService.registerPaper(paperForm); 
		BigInteger paperId = paperForm.getPaperId();//登録論文ID取得
		
		/* 引用情報登録処理 */
		//先行論文を登録
		if(form.getPrePaperList() != null) {
			for(BigInteger citedId: form.getPrePaperList()) {
				paperService.registerCitation(paperId, citedId);
			}
		}
		//後続論文を登録
		if(form.getSubPaperList() != null) {
			for(BigInteger citingId: form.getSubPaperList()) {
				paperService.registerCitation(citingId, paperId);
			}
		}
		
		
		return "redirect:/home/papers";
	}
}
