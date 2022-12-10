package com.encore.auction.controller.notice.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public final class NoticeUpdateRequest {
	@NotEmpty
	@Pattern(regexp = "^[0-9A-Za-z]{2,12}$", message = "아이디는 2~12자로 영문 대소문자, 숫자만 사용할 수 있습니다.")
	private final String managerId;
	@NotEmpty
	@Size(max = 50, message = "제목은 최대 50자까지 입력 가능합니다.")
	private final String title;
	@NotEmpty
	@Size(max = 3000, message = "내용은 최대 3000자까지 입력 가능합니다.")
	private final String content;

	public NoticeUpdateRequest(String managerId, String title, String content) {
		this.managerId = managerId;
		this.title = title;
		this.content = content;
	}
}
