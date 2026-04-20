package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	
	//読むべき論文一覧
	@GetMapping("/toread")
	public String getToread(@AuthenticationPrincipal org.springframework.security.core.userdetails.User loginUser, Model model) {
		MUser user = userService.getLoginUser(loginUser.getUsername());
		model.addAttribute("loginUser", user);
		return "home/toread";
	}

	//マイページ
	@GetMapping("/mypage")
	public String getMypage(@AuthenticationPrincipal org.springframework.security.core.userdetails.User loginUser, Model model) {
		MUser user = userService.getLoginUser(loginUser.getUsername());
		model.addAttribute("loginUser", user);
		//マイページを表示
		return "home/mypage";
	}
}
