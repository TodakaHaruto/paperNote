package com.example.demo.domain.user.service;

import java.time.LocalDate;

import com.example.demo.domain.user.model.MUser;

public interface UserService {
	/* ユーザ登録 */
	public void signup(MUser user);
	
	
	/* ログインユーザ情報取得 */
	public MUser getLoginUser(String userId);
	
	/* ユーザ情報更新（パスワード更新なし） */
	public void updateUserOne(String oldUserId,
							String newUserId,
							String userName,
							LocalDate birthday,
							int gender);
	
	/* パスワード変更 */
	public void updatePass(String userId, String password);

}
