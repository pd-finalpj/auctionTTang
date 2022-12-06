package com.encore.auction.controller.user.responses;

import lombok.Getter;

@Getter
public final class UserDetailsResponse {

	private final String userId;

	private final String name;

	private final Integer age;

	private final String nickname;

	private final String phoneNumber;

	private final String email;

	public UserDetailsResponse(String userId, String name, Integer age, String nickname, String phoneNumber,
		String email) {
		this.userId = userId;
		this.name = name;
		this.age = age;
		this.nickname = nickname;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
}
