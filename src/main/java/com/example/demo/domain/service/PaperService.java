package com.example.demo.domain.service;

import java.math.BigInteger;
import java.util.List;

import com.example.demo.domain.paper.model.MPaper;
import com.example.demo.domain.user.model.MUser;

public interface PaperService {
	/* 論文一覧を取得 */
	public List<MPaper> getPapers(MUser user);
	
	/* 論文一件取得 */
	public MPaper getPaper(BigInteger paperId);
}
