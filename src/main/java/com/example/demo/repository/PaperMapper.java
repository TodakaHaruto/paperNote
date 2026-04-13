package com.example.demo.repository;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.paper.model.MCitation;
import com.example.demo.domain.paper.model.MPaper;
import com.example.demo.domain.user.model.MUser;

@Mapper
public interface PaperMapper {
	/* 論文一覧取得 */
	public List<MPaper> findMany(MUser user);
	
	/* 論文一件検索 */
	public MPaper findOne(BigInteger paperId);
	
	/* 先行研究論文取得 */
	public List<MCitation> findPreCitations(BigInteger paperId);
	
	/* 後続研究論文を取得 */
	public List<MCitation> findSubCitations(BigInteger paperId);
}
