package com.example.demo.form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class PasswordForm {
	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String userId;
	
	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	@Length(min = 8, max = 30, groups = ValidGroup2.class)
	private String password;
	
	private String confirmPassword;
	
	@AssertTrue(groups = ValidGroup1.class)
    public boolean isPasswordMatched() {
        if (password == null || confirmPassword == null) {
            return false;
        }
        return password.equals(confirmPassword);
    }
}
