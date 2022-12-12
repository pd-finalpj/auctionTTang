package com.encore.auction.controller.notice.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public final class NoticeRegisterRequest {

	@NotEmpty
	@Size(max = 50, message = "제목은 최대 50자까지 입력 가능합니다.")
	private final String title;
	@NotEmpty
	@Size(max = 3000, message = "내용은 최대 3000자까지 입력 가능합니다.")
	private final String content;

	public NoticeRegisterRequest(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
