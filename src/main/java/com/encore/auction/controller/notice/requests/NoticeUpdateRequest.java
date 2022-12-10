package com.encore.auction.controller.notice.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public final class NoticeUpdateRequest {
	@NotEmpty
	@Size(max = 50)
	private final String title;
	@NotEmpty
	@Size(max = 3000)
	private final String content;

	public NoticeUpdateRequest(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
