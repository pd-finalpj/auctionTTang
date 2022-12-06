package com.encore.auction.controller.user.requests;

import lombok.Getter;

@Getter
public final class UserLoginRequest {

	private final String userId;
	private final String password;

	public UserLoginRequest(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
}
