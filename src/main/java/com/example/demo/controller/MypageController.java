package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.GroupOrder;
import com.example.demo.form.UpdateForm;

@Controller
@RequestMapping("/home/user-info")
public class MypageController {
	
	@Autowired
	private UserService userService;
	
	//変更画面を表示
	@PostMapping(value = "/update", params = "display")
	public String postUpdateMypage(Model model, @ModelAttribute UpdateForm form) {
		model.addAttribute("updateForm", form);
		return "/home/user-info/update";
	}
	
	//ユーザ情報の変更を実行
	@PostMapping(value = "/update", params = "update")
	public String updateUserInfo( Model model, @ModelAttribute @Validated(GroupOrder.class) UpdateForm form, BindingResult bindingResult) {
	
		//バリデーションチェック
		if(bindingResult.hasErrors()) {
			//登録画面に戻る
			return "/home/user-info/update";
		}
		
		userService.updateUserOne(form.getOldUserId(),
								form.getNewUserId(),
								form.getUserName(),
								form.getBirthday(),
								form.getGender());
		return "/home/user-info/complete";
	}
	
	@PostMapping(value = "/update-pass")
	public String updateUserPass(@AuthenticationPrincipal org.springframework.security.core.userdetails.User loginUser, Model model, @ModelAttribute @Validated(GroupOrder.class) UpdateForm form, BindingResult bindingResult) {
		MUser user = userService.getLoginUser(loginUser.getUsername());
		model.addAttribute("loginUser", user);
		
		//バリデーションチェック
		if(bindingResult.hasErrors()) {
			//登録画面に戻る
			return "/home/user-info/update";
		}
		return "/home/user-info/update-pass";
	}
}
