package com.encore.auction.controller.filtering.requests;

import lombok.Getter;

@Getter
public final class FilteringAuctionItemRequest {

	private final String addressCode;

	private final String date;

	private final String category;

	private final Integer auctionFailedCount;

	private final SortCategory sortCategory;

	private final Integer pageNum;

	private final Integer amount;

	public FilteringAuctionItemRequest(String addressCode, String date, String category, Integer auctionFailedCount,
		SortCategory sortCategory, Integer pageNum, Integer amount) {
		this.addressCode = addressCode;
		this.date = date;
		this.category = category;
		this.auctionFailedCount = auctionFailedCount;
		this.sortCategory = sortCategory;
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
