package com.example.demo.controller;

import java.math.BigInteger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.paper.model.MPaper;
import com.example.demo.domain.service.PaperService;
import com.example.demo.form.PaperDetailForm;

@Controller
@RequestMapping("home/")
public class PaperDetailController {

		@Autowired
		private PaperService paperService;
		
		@Autowired
		private ModelMapper modelMapper;
	
		@GetMapping("detail/{paperId}")
		public String getPaper(PaperDetailForm form, Model model, @PathVariable("paperId") BigInteger paperId) {
			//論文情報を取得
			MPaper paper = paperService.getPaper(paperId);
			
			//論文情報をformに変換
			form = modelMapper.map(paper, PaperDetailForm.class);
			
			System.out.println(form.getTitle());
			System.out.println(form.getReadDate());
			System.out.println(form.getPaperUrl());
			
			model.addAttribute("userDetailForm", form);
			
			return "/home/detail";
		}
}
