package com.encore.auction.controller.manager.requests;

import lombok.Getter;

@Getter
public final class ManagerSignUpRequest {

	private final String managerId;

	private final String password;

	private final String passwordCheck;

	private final String name;

	private final Integer age;

	private final String phoneNumber;

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
