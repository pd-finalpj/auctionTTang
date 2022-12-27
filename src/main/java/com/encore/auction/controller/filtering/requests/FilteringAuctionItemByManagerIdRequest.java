package com.encore.auction.controller.filtering.requests;

import lombok.Getter;

@Getter
public final class FilteringAuctionItemByManagerIdRequest {

	private final Integer pageNum;

	private final Integer amount;

	public FilteringAuctionItemByManagerIdRequest(Integer pageNum, Integer amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
