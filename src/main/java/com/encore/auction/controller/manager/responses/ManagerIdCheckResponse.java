package com.encore.auction.controller.manager.responses;

import lombok.Getter;

@Getter
public final class ManagerIdCheckResponse {

	private final boolean managerIdExist;

	public ManagerIdCheckResponse(boolean managerIdExist) {
		this.managerIdExist = managerIdExist;
	}
}
