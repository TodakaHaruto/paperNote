package com.example.demo.form;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PaperDetailForm {
	private BigInteger paperId;
	private BigInteger userSerial;
	private String title;
	private String paperUrl;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date readDate;
	private String learningNote;
	
	private List<BigInteger> preCitationList;
	private List<BigInteger> subCitationList;
}
