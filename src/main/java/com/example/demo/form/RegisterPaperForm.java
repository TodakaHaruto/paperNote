package com.example.demo.form;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RegisterPaperForm {
	private BigInteger paperId;
	private String title;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date readDate;
	private String paperUrl;
	private String learningNote;
	private List<BigInteger> prePaperList;
	private List<BigInteger> subPaperList;
}
