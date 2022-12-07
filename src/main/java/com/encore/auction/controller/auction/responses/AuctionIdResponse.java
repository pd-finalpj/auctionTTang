package com.encore.auction.controller.auction.responses;

import lombok.Getter;

@Getter
public class AuctionIdResponse {

	private final long auctionItemId;

	public AuctionIdResponse(long auctionItemId) {
		this.auctionItemId = auctionItemId;
	}
}
