package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class SuccessLogin {

	//ログイン成功画面
	@GetMapping("success")
	public String getSuccessLogin() {
		//ログイン成功画面を表示
		return "user/successLogin";
	}
}
