package com.example.demo.domain.paper.model;

import java.math.BigInteger;
import java.time.LocalDate;

import lombok.Data;

@Data
public class MPaper {
	private BigInteger paperId;
	private BigInteger userSerial;
	private String title;
	private String paperUrl;
	private LocalDate readDate;
	private String learningNote;
}
