package com.encore.auction.controller.manager.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public class ManagerDeleteRequest {
	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
	private final String password;

	public ManagerDeleteRequest(String password) {
		this.password = password;
	}
}
