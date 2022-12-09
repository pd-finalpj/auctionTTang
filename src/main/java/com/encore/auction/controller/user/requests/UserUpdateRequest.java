package com.encore.auction.controller.user.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public final class UserUpdateRequest {

	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
	private final String oldPassword;

	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
	private final String newPassword;

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
	@Pattern(regexp = "^[0-9A-Za-z가-힣]{2,11}$")
	private final String nickname;

	@NotEmpty
	@Pattern(regexp = "^[0-9]{11}$")
	private final String phoneNumber;

	@NotEmpty
	@Email
	private final String email;

	public UserUpdateRequest(String oldPassword, String newPassword, String passwordCheck, String name, Integer age,
		String nickname, String phoneNumber, String email) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.passwordCheck = passwordCheck;
		this.name = name;
		this.age = age;
		this.nickname = nickname;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
}
