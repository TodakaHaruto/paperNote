package com.example.demo.domain.paper.model;

import java.util.Date;

import lombok.Data;

@Data
public class MPaper {
	private String userSerial;
	private String title;
	private Date readDate;
	private String learningNote;
}
