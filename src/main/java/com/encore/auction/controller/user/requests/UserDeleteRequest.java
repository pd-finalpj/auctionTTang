package com.encore.auction.controller.user.requests;

import lombok.Getter;

@Getter
public final class UserDeleteRequest {

	private final String password;

	public UserDeleteRequest(String password) {
		this.password = password;
	}
}
