package com.example.demo.domain.paper.model;

import java.math.BigInteger;

import lombok.Data;

@Data
public class MCitation {
	private BigInteger citingPaperId;
	private String citingPaperTitle;
	private BigInteger citedPaperId;
	private String citedPaperTitle;
}
