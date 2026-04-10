package com.example.demo.controller;

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

import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.GroupOrder;
import com.example.demo.form.PasswordForm;
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
	
	/* パスワードの更新画面を表示 */
	@GetMapping("/update-pass")
	public String getUpdateUserPass(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User loginUser) {
		PasswordForm passwordForm = new PasswordForm();
		passwordForm.setUserId(loginUser.getUsername());
		model.addAttribute("passwordForm", passwordForm);
		return "/home/user-info/update-pass";
	}
	
	/* パスワードを変更 */
	@PostMapping("/update-pass")
	public String updateUserPass(Model model, @ModelAttribute @Validated(GroupOrder.class) PasswordForm form, BindingResult bindingResult) {
		//バリデーションチェック
		if(bindingResult.hasErrors()) {
			//登録画面に戻る
			return "/home/user-info/update-pass";
		}
		//パスワード更新処理
		userService.updatePass(form.getUserId(), form.getPassword());
		
		return "/home/user-info/complete";
	}
}
