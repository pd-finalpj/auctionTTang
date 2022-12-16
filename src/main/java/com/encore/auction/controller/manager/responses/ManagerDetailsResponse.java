package com.encore.auction.controller.manager.responses;

import lombok.Getter;

@Getter
public class ManagerDetailsResponse {

	private final String managerId;

	private final String name;

	private final String birth;

	private final String court;

	private final String department;

	private final String phoneNumber;

	private final String email;

	public ManagerDetailsResponse(String managerId, String name, String birth, String court, String department,
		String phoneNumber, String email) {
		this.managerId = managerId;
		this.name = name;
		this.birth = birth;
		this.court = court;
		this.department = department;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
}
