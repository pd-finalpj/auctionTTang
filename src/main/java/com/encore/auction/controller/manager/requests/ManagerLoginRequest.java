package com.encore.auction.controller.manager.requests;

import lombok.Getter;

@Getter
public final class ManagerLoginRequest {

	private final String managerId;
	private final String password;

	public ManagerLoginRequest(String managerId, String password) {
		this.managerId = managerId;
		this.password = password;
	}
}
