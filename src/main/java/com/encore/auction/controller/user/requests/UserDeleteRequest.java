package com.encore.auction.controller.user.requests;

import lombok.Getter;

@Getter
public final class UserDeleteRequest {

	private final String userId;
	private final String password;

	public UserDeleteRequest(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
}
