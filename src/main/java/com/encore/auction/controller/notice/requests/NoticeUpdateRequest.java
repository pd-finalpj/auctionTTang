package com.encore.auction.controller.notice.requests;

import lombok.Getter;

@Getter
public final class NoticeUpdateRequest {

	private final String managerId;

	private final String title;

	private final String content;

	public NoticeUpdateRequest(String managerId, String title, String content) {
		this.managerId = managerId;
		this.title = title;
		this.content = content;
	}
}
