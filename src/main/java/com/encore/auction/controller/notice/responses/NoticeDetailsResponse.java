package com.encore.auction.controller.notice.responses;

import lombok.Getter;

@Getter
public final class NoticeDetailsResponse {

	private final Long noticeId;

	private final String managerId;

	private final String managerName;

	private final String title;

	private final String content;

	public NoticeDetailsResponse(Long noticeId, String managerId, String managerName, String title, String content) {
		this.noticeId = noticeId;
		this.managerId = managerId;
		this.managerName = managerName;
		this.title = title;
		this.content = content;
	}
}
