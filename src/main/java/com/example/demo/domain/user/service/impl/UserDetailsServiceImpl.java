package com.example.demo.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//ユーザ情報を取得
		MUser loginUser = service.getLoginUser(username);
		
		//ユーザが存在しない場合の処理
		if(loginUser == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		return User.withUsername(loginUser.getUserId())
					.password(loginUser.getPassword())
					.roles("USER")
					.build();
		}
	
}
