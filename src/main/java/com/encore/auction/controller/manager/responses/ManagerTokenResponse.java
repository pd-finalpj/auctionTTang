package com.encore.auction.controller.manager.responses;

import lombok.Getter;

@Getter
public final class ManagerTokenResponse {

	private final String accessToken;
	private final String tokenType;

	public ManagerTokenResponse(String accessToken, String tokenType) {
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}
}
