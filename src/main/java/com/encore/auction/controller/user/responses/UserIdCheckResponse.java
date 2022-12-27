package com.encore.auction.controller.user.responses;

import lombok.Getter;

@Getter
public final class UserIdCheckResponse {

	private final boolean userIdExist;

	public UserIdCheckResponse(boolean userIdExist) {
		this.userIdExist = userIdExist;
	}
}
