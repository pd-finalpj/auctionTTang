package com.encore.auction.controller.user.responses;

import lombok.Getter;

@Getter
public final class UserTokenResponse {

	private final String accessToken;
	private final String tokenType;

	public UserTokenResponse(String accessToken, String tokenType) {
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}
}
