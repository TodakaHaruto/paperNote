package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.paper.model.MPaper;
import com.example.demo.domain.service.PaperService;
import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;

@Controller
@RequestMapping("/home")
public class PaperListController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PaperService paperService;
	
	/* 論文一覧を表示 */
	@GetMapping("/papers")
	public String getPaperList(@AuthenticationPrincipal org.springframework.security.core.userdetails.User loginUser, Model model) {
		MUser user = userService.getLoginUser(loginUser.getUsername());
		model.addAttribute("loginUser", user);
		
		//論文一覧を取得
		List<MPaper> paperList = paperService.getPapers(user);
		
		//Modelに登録
		model.addAttribute("paperList", paperList);
		
		return "/home/papers";
	}
}
