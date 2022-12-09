package com.encore.auction.controller.manager.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public final class ManagerSignUpRequest {
	@NotEmpty
	@Pattern(regexp = "^[0-9A-Za-z]{2,12}$")
	private final String managerId;
	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
	private final String password;
	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
	private final String passwordCheck;
	@NotEmpty
	@Pattern(regexp = "^[A-Za-zㄱ-ㅎ가-힣]{2,5}$")
	private final String name;
	@NotNull
	@Max(99)
	private final Integer age;
	@NotEmpty
	@Pattern(regexp = "^[0-9]{11}$")
	private final String phoneNumber;
	@NotEmpty
	@Email
	private final String email;

	public ManagerSignUpRequest(String managerId, String password, String passwordCheck, String name, Integer age,
		String phoneNumber,
		String email) {
		this.managerId = managerId;
		this.password = password;
		this.passwordCheck = passwordCheck;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
}
