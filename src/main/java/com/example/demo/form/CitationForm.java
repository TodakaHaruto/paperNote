package com.example.demo.form;

import java.math.BigInteger;

import lombok.Data;

@Data
public class CitationForm {
	private BigInteger citingPaperId;
	private String citingPaperTitle;
	private BigInteger citedPaperId;
	private String citedPaperTitle;
}
