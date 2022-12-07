package com.encore.auction.controller.manager.responses;

import lombok.Getter;

@Getter
public final class ManagerIdResponse {

	private final String managerId;

	public ManagerIdResponse(String managerId) {
		this.managerId = managerId;
	}

}
