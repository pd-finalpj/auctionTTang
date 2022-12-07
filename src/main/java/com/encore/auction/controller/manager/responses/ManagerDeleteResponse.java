package com.encore.auction.controller.manager.responses;

import lombok.Getter;

@Getter
public class ManagerDeleteResponse {
	private final String managerId;

	private final boolean state;

	public ManagerDeleteResponse(String managerId, boolean state) {
		this.managerId = managerId;
		this.state = state;
	}


}
