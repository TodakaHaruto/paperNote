package com.example.demo.repository;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.paper.model.MPaper;
import com.example.demo.domain.user.model.MUser;

@Mapper
public interface PaperMapper {
	/* 論文一覧取得 */
	public List<MPaper> findMany(MUser user);
	
	/* 論文一件検索 */
	public MPaper findOne(BigInteger paperId);
}
