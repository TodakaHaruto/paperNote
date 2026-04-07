package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.user.model.MUser;

@Mapper
public interface UserMapper {
	
	/* ユーザ登録 */
	public int insertOne(MUser user);
	
	
	
	/* ログインユーザ取得 */
	public MUser findLoginUser(String userId);

	
	/* ユーザ更新(パスワード更新なし) */
	public void updateOne(@Param("userId") String userId,
							@Param("newUserId") String newUserId,
							@Param("password") String password,
							@Param("userName") String userName,
							@Param("birthday") String birthday,
							@Param("gender") int gender);
	
	
}
