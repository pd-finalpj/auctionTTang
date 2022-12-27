package com.encore.auction.controller.notice.requests;

import lombok.Getter;

@Getter
public final class NoticeRetrieveRequest {

	private final Integer pageNum;

	private final Integer amount;

	public NoticeRetrieveRequest(Integer pageNum, Integer amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
