package com.encore.auction.controller.notice.responses;

import java.util.List;

import lombok.Getter;

@Getter
public final class NoticeDetailsListResponse {

	private final int endPage;
	private final int selectPage;
	private final int amountItem;
	private final List<NoticeDetailsResponse> noticeDetailsResponseList;

	public NoticeDetailsListResponse(int endPage, int selectPage, int amountItem,
		List<NoticeDetailsResponse> noticeDetailsResponseList) {
		this.endPage = endPage;
		this.selectPage = selectPage;
		this.amountItem = amountItem;
		this.noticeDetailsResponseList = noticeDetailsResponseList;
	}
}
