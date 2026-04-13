package com.example.demo.domain.service;

import java.math.BigInteger;
import java.util.List;

import com.example.demo.domain.paper.model.MCitation;
import com.example.demo.domain.paper.model.MPaper;
import com.example.demo.domain.user.model.MUser;

public interface PaperService {
	/* 論文一覧を取得 */
	public List<MPaper> getPapers(MUser user);
	
	/* 論文一件取得 */
	public MPaper getPaper(BigInteger paperId);
	
	/* 先行研究論文を取得 */
	public List<MCitation> getPreCitations(BigInteger paperId);
	
	/* 後続研究論文を取得 */
	public List<MCitation> getSubCitations(BigInteger paperId);
}
