package com.encore.auction.controller.auction.responses;

import lombok.Getter;

@Getter
public class AuctionDeleteResponse {

	private final Long auctionItemId;

	private final boolean state;

	public AuctionDeleteResponse(Long auctionItemId, boolean state) {
		this.auctionItemId = auctionItemId;
		this.state = state;
	}
}
