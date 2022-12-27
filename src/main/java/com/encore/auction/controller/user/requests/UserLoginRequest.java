package com.encore.auction.controller.user.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public final class UserLoginRequest {
	@NotEmpty
	@Pattern(regexp = "^[0-9A-Za-z]{2,12}$", message = "아이디는 2~12자로 영문 대소문자, 숫자만 사용할 수 있습니다.")
	private final String userId;
	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "비밀번호는 8자 이상으로 영문 대소문자, 숫자, 특수기호를 조합해서 사용하세요.")
	private final String password;

	public UserLoginRequest(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
}
