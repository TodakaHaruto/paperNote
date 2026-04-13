package com.example.demo.form;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

@Data
public class PaperDetailForm {
	private BigInteger paperId;
	private BigInteger userSerial;
	private String title;
	private String paperUrl;
	private Date readDate;
	private String learningNote;
}
