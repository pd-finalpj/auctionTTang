package com.encore.auction.controller.manager.requests;

import lombok.Getter;
@Getter
public class ManagerDeleteRequest {

	private final String password;

	public ManagerDeleteRequest(String password) { this.password = password; }
}
