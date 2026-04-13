package com.example.demo.domain.service.imple;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.paper.model.MPaper;
import com.example.demo.domain.service.PaperService;
import com.example.demo.domain.user.model.MUser;
import com.example.demo.repository.PaperMapper;

@Service
public class PaperServiceImpl implements PaperService {
	@Autowired PaperMapper mapper;
	
	/* 論文一覧取得 */
	@Override
	public List<MPaper> getPapers(MUser user) {
		return mapper.findMany(user);
	}
	
	@Override
	public MPaper getPaper(BigInteger paperId) {
		return mapper.findOne(paperId);
	}
}
