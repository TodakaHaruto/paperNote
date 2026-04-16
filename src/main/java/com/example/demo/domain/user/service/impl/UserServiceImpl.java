package com.example.demo.domain.user.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	/* ユーザ登録 */
	@Override
	@Transactional
	public void signup(MUser user) {
		
		//パスワード暗号化
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		
		mapper.insertOne(user);
	}
	
	/* ログインユーザ情報取得 */
	@Override
	public MUser getLoginUser(String userId) {
		return mapper.findLoginUser(userId);
	}
	
	/* ユーザ情報更新（パスワード更新なし） */
	@Override
	@Transactional
	public void updateUserOne(String oldUserId,
							String newUserId,
							String userName,
							LocalDate birthday,
							int gender) {
		mapper.updateOne(oldUserId, newUserId, userName, birthday, gender);
	}
	
	
	/* パスワード変更 */
	@Override
	@Transactional
	public void updatePass(String userId, String password) {
		mapper.updatePass(userId, encoder.encode(password));
	}
}
