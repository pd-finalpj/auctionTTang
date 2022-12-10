package com.encore.auction.controller.user.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public final class UserDeleteRequest {
	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "비밀번호는 8자 이상으로 영문 대소문자, 숫자, 특수기호를 조합해서 사용하세요.")
	private final String password;

	public UserDeleteRequest(String password) {
		this.password = password;
	}
}
