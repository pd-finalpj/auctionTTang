package com.encore.auction.controller.notice.requests;

import lombok.Getter;

@Getter
public final class NoticeRegisterRequest {

	private final String managerId;

	private final String title;

	private final String content;

	public NoticeRegisterRequest(String managerId, String title, String content) {
		this.managerId = managerId;
		this.title = title;
		this.content = content;
	}
}
