package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.paper.model.MPaper;
import com.example.demo.domain.service.PaperService;
import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.PaperDetailForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/home")
@Slf4j
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
		model.addAttribute("paperDetailForm", new PaperDetailForm());
		return "home/register";
	}
	
	@PostMapping("/register")
	public String postRegister(@AuthenticationPrincipal org.springframework.security.core.userdetails.User loginUser, Model model, @ModelAttribute @Validated PaperDetailForm form, BindingResult bindingResult) {
		
		//バリデーション
		if(bindingResult.hasErrors()) {
			//ログイン中ユーザの登録論文一覧を取得
			MUser user = userService.getLoginUser(loginUser.getUsername());
			List<MPaper> paperList = paperService.getPapers(user);
			model.addAttribute("paperList", paperList);
			//登録画面に戻る
			return "home/register";
		}
		
		//ユーザ情報を取得
		MUser user = userService.getLoginUser(loginUser.getUsername());
		
		//formにユーザ情報を格納
		form.setUserSerial(user.getUserSerial());
		
		//登録処理を実行
		try {
			paperService.registerPaperWithCitations(form);
		} catch (Exception e) {
			log.error("論文情報登録でエラー", e);
		}
		
		return "redirect:home/papers";
	}
}
