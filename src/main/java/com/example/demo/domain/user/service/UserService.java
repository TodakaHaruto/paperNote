package com.example.demo.domain.user.service;

import com.example.demo.domain.user.model.MUser;

public interface UserService {
	/* ユーザ登録 */
	public void signup(MUser user);
	
	
	/* ログインユーザ情報取得 */
	public MUser getLoginUser(String userId);
}
