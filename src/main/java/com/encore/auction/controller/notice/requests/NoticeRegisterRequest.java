package com.encore.auction.controller.notice.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public final class NoticeRegisterRequest {
	@NotEmpty
	@Pattern(regexp = "^[0-9A-Za-z]{2,12}$")
	private final String managerId;
	@NotEmpty
	@Size(max = 50)
	private final String title;
	@NotEmpty
	@Size(max = 3000)
	private final String content;

	public NoticeRegisterRequest(String managerId, String title, String content) {
		this.managerId = managerId;
		this.title = title;
		this.content = content;
	}
}
