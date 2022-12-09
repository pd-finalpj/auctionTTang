package com.encore.auction.controller.user.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public final class UserLoginRequest {
	@NotEmpty
	@Pattern(regexp = "^[0-9A-Za-z]{2,12}$")
	private final String userId;
	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
	private final String password;

	public UserLoginRequest(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
}
