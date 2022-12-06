package com.encore.auction.controller.user.responses;

import lombok.Getter;

@Getter
public final class UserDeleteResponse {

	private final String userId;
	private final boolean state;

	public UserDeleteResponse(String userId, boolean state) {
		this.userId = userId;
		this.state = state;
	}
}
