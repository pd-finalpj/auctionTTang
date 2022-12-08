package com.encore.auction.controller.notice.responses;

import lombok.Getter;

@Getter
public final class NoticeIdResponse {

	private final Long noticeId;

	public NoticeIdResponse(Long noticeId) {
		this.noticeId = noticeId;
	}
}
