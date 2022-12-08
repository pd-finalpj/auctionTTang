package com.encore.auction.controller.notice.responses;

import lombok.Getter;

@Getter
public final class NoticeDeleteResponse {

	private final Long noticeId;

	private final boolean state;

	public NoticeDeleteResponse(Long noticeId, boolean state) {
		this.noticeId = noticeId;
		this.state = state;
	}
}
