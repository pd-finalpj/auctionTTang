package com.encore.auction.controller.manager.responses;

import lombok.Getter;

@Getter
public class ManagerDetailsResponse {

	private final String managerId;

	private final String name;

	private final Integer age;

	private final String nickname;

	private final String phoneNumber;

	private final String email;

	public ManagerDetailsResponse(String managerId, String name, Integer age, String nickname, String phoneNumber,
		String email) {
		this.managerId = managerId;
		this.name = name;
		this.age = age;
		this.nickname = nickname;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
}
