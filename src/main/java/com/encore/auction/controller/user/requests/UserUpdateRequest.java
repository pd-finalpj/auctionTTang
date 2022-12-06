package com.encore.auction.controller.user.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public final class UserUpdateRequest {

	@NotEmpty
	private final String oldPassword;

	@NotEmpty
	private final String newPassword;

	@NotEmpty
	private final String passwordCheck;

	@NotEmpty
	private final String name;

	@NotNull
	private final Integer age;

	@NotEmpty
	private final String nickname;

	@NotEmpty
	private final String phoneNumber;

	@NotEmpty
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
