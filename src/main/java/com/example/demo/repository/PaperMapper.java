package com.example.demo.repository;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.paper.model.MCitation;
import com.example.demo.domain.paper.model.MPaper;
import com.example.demo.domain.user.model.MUser;
import com.example.demo.form.PaperDetailForm;

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
	
	/* 読んだ論文を登録 */
	public void insertOne(PaperDetailForm form);
	
	/* 引用情報を登録 */
	public void insertCitation(@Param("citingPaperId") BigInteger citingPaperId, @Param("citedPaperId") BigInteger citedPaperId);
}
