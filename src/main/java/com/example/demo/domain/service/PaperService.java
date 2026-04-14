package com.example.demo.domain.service;

import java.math.BigInteger;
import java.util.List;

import com.example.demo.domain.paper.model.MCitation;
import com.example.demo.domain.paper.model.MPaper;
import com.example.demo.domain.user.model.MUser;
import com.example.demo.form.PaperDetailForm;

public interface PaperService {
	/* 論文一覧を取得 */
	public List<MPaper> getPapers(MUser user);
	
	/* 論文一件取得 */
	public MPaper getPaper(BigInteger paperId);
	
	/* 先行研究論文を取得 */
	public List<MCitation> getPreCitations(BigInteger paperId);
	
	/* 後続研究論文を取得 */
	public List<MCitation> getSubCitations(BigInteger paperId);
	
	/* 読んだ論文を登録 */
	public void registerPaper(PaperDetailForm form);
	
	/* 引用情報を登録 */
	public void registerCitation(BigInteger citingPaperId, BigInteger citedPaperId);
	
	/* 記録を削除 */
	public void deletePaper(BigInteger paperId);
}
