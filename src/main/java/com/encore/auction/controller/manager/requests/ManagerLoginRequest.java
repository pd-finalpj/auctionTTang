package com.encore.auction.controller.manager.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public final class ManagerLoginRequest {
	@NotEmpty
	@Pattern(regexp = "^[0-9A-Za-z]{2,12}$")
	private final String managerId;
	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
	private final String password;

	public ManagerLoginRequest(String managerId, String password) {
		this.managerId = managerId;
		this.password = password;
	}
}
