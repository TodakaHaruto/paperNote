package com.example.demo.form;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PaperDetailForm {
	private BigInteger paperId;
	private BigInteger userSerial;
	@NotBlank
	private String title;
	private String paperUrl;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate readDate;
	private String learningNote;
	
	private List<BigInteger> preCitationList;
	private List<BigInteger> subCitationList;
}
