package com.example.demo.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.application.service.UserApplicationService;


@Controller
@RequestMapping("/user")
public class SignupController {
	
	@Autowired
	private UserApplicationService userApplicationService;
	
	/*ユーザ登録画面を表示*/
	@GetMapping("/signup")
	public String getSignup(Model model, Locale locale) {
		
		Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
		model.addAttribute("genderMap", genderMap);
		
		return "user/signup";
	}
	
	//ユーザ登録
	@PostMapping("/signup")
	public String postSignup() {
		return "redirect:/login";
	}
}
