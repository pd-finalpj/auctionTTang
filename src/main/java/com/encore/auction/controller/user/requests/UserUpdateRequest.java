package com.encore.auction.controller.user.requests;

import lombok.Getter;

@Getter
public final class UserUpdateRequest {

	private final String password;

	private final String passwordCheck;

	private final String name;

	private final Integer age;

	private final String nickname;

	private final String phoneNumber;

	private final String email;

	public UserUpdateRequest(String password, String passwordCheck, String name, Integer age, String nickname,
		String phoneNumber, String email) {
		this.password = password;
		this.passwordCheck = passwordCheck;
		this.name = name;
		this.age = age;
		this.nickname = nickname;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
}
