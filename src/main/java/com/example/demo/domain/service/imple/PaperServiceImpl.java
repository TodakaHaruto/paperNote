package com.example.demo.domain.service.imple;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	/* 記録を削除 */
	@Override
	public void deletePaper(BigInteger paperId) {
		mapper.deleteOne(paperId);
	}
	
	/* 論文情報を更新 */
	@Override
	public void updatePaper(PaperDetailForm form) {
		mapper.updateOne(form);
	}
	
	/* 引用情報を削除 */
	@Override
	public void deleteCitations(BigInteger paperId) {
		mapper.deleteCitations(paperId);
	}
	
	/* 読んだ論文と引用情報の登録 */
	@Override
	@Transactional
	public void registerPaperWithCitations(PaperDetailForm form) {
		registerPaper(form); 
		BigInteger paperId = form.getPaperId();//登録論文ID取得
		
		/* 引用情報登録処理 */
		//先行論文を登録
		if(form.getPreCitationList() != null) {
			for(BigInteger citedId: form.getPreCitationList()) {
				registerCitation(paperId, citedId);
			}
		}
		//後続論文を登録
		if(form.getSubCitationList() != null) {
			for(BigInteger citingId: form.getSubCitationList()) {
				registerCitation(citingId, paperId);
			}
		}
	}
	
	/*論文情報と引用情報の更新*/
	@Transactional
	@Override
	public void updatePaperWithCitations (PaperDetailForm form) {
		//論文IDの取得
		BigInteger paperId = form.getPaperId();
		
		//論文情報の更新を実行
		updatePaper(form);
		
		//更新論文引用情報の削除
		deleteCitations(paperId);
		
		/* 引用情報登録処理 */
		//先行論文を登録
		if(form.getPreCitationList() != null) {
			for(BigInteger citedId: form.getPreCitationList()) {
				registerCitation(paperId, citedId);
			}
		}
		//後続論文を登録
		if(form.getSubCitationList() != null) {
			for(BigInteger citingId: form.getSubCitationList()) {
				registerCitation(citingId, paperId);
			}
		}
	}
}
