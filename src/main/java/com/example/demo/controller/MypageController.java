package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.UpdateForm;

@Controller
@RequestMapping("/home/user-info")
public class MypageController {
	@PostMapping(value = "/update", params = "display")
	public String postUpdateMypage(Model model, @ModelAttribute UpdateForm form) {
		model.addAttribute("updateForm", form);
		return "/home/user-info/update";
	}
	
	@PostMapping(value = "/update", params = "update")
	public String updateUserInfo() {
		return "redirect:/home/mypage";
	}
}
