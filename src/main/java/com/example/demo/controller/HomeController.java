package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	//読んだ論文一覧
	@GetMapping("/home")
	public String getHome() {
		return "home/home";
	}
	
	//読むべき論文一覧
	@GetMapping("/toread")
	public String getToread() {
		//マイページを表示
		return "/home/toread";
	}

	//マイページ
	@GetMapping("/mypage")
	public String getMypage() {
		//マイページを表示
		return "/home/mypage";
	}
}
