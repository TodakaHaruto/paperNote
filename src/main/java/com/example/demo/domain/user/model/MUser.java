package com.example.demo.domain.user.model;

import java.math.BigInteger;
import java.time.LocalDate;

import lombok.Data;

@Data
public class MUser {
	private BigInteger userSerial;
	private String userId;
	private String password;
	private String userName;
	private LocalDate birthday;
	private int gender;
}
