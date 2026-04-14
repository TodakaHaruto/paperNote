package com.example.demo.domain.service.imple;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.paper.model.MCitation;
import com.example.demo.domain.paper.model.MPaper;
import com.example.demo.domain.service.PaperService;
import com.example.demo.domain.user.model.MUser;
import com.example.demo.form.PaperDetailForm;
import com.example.demo.repository.PaperMapper;

@Service
public class PaperServiceImpl implements PaperService {
	@Autowired PaperMapper mapper;
	
	/* 論文一覧取得 */
	@Override
	public List<MPaper> getPapers(MUser user) {
		return mapper.findMany(user);
	}
	
	/* 論文一件検索 */
	@Override
	public MPaper getPaper(BigInteger paperId) {
		return mapper.findOne(paperId);
	}
	
	/* 先行研究論文を取得 */
	@Override
	public List<MCitation> getPreCitations(BigInteger paperId) {
		return mapper.findPreCitations(paperId);
	}
	
	/* 後続研究論文を取得 */
	@Override
	public List<MCitation> getSubCitations(BigInteger paperId) {
		return mapper.findSubCitations(paperId);
	}
	
	/* 読んだ論文を登録 */
	@Override
	public void registerPaper(PaperDetailForm form) {
		mapper.insertOne(form);
	}
	
	/* 引用情報を登録 */
	@Override
	public void registerCitation(BigInteger citingPaperId, BigInteger citedPaperId) {
		mapper.insertCitation(citingPaperId, citedPaperId);
	}
	
}
