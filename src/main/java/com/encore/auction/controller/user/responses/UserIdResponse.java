package com.encore.auction.controller.user.responses;

import lombok.Getter;

@Getter
public final class UserIdResponse {

	private final String userId;

	public UserIdResponse(String userId) {
		this.userId = userId;
	}
}
