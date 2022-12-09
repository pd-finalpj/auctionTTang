package com.encore.auction.controller.user.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public final class UserDeleteRequest {
	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
	private final String password;

	public UserDeleteRequest(String password) {
		this.password = password;
	}
}
