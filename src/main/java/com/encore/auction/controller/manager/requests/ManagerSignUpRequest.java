package com.encore.auction.controller.manager.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public final class ManagerSignUpRequest {
	@NotEmpty
	@Pattern(regexp = "^[0-9A-Za-z]{2,12}$", message = "아이디는 2~12자로 영문 대소문자, 숫자만 사용할 수 있습니다.")
	private final String managerId;
	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "비밀번호는 8자 이상으로 영문 대소문자, 숫자, 특수기호를 조합해서 사용하세요.")
	private final String password;
	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "비밀번호는 8자 이상으로 영문 대소문자, 숫자, 특수기호를 조합해서 사용하세요.")
	private final String passwordCheck;
	@NotEmpty
	@Pattern(regexp = "^[A-Za-zㄱ-ㅎ가-힣]{2,5}$", message = "이름은 2~5자로 사용 가능합니다.")
	private final String name;
	@NotEmpty
	private final String court;
	@NotEmpty
	private final String department;
	@NotNull
	@Max(99)
	private final Integer age;
	@NotEmpty
	@Pattern(regexp = "^[0-9]{11}$", message = "전화번호는 '-'없이 숫자 11자리로 입력해 주세요.")
	private final String phoneNumber;
	@NotEmpty
	@Email(message = "이메일 형식에 맞지 않는 메일 주소입니다. 다시 입력해 주세요.")
	private final String email;

	public ManagerSignUpRequest(String managerId, String password, String passwordCheck, String name, String court,
		String department, Integer age, String phoneNumber, String email) {
		this.managerId = managerId;
		this.password = password;
		this.passwordCheck = passwordCheck;
		this.name = name;
		this.court = court;
		this.department = department;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
}
