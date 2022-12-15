package com.encore.auction.controller.auction.responses;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public final class AuctionOverDateResponse {

	private final Long auctionItemId;

	private final LocalDateTime auctionStartDate;
	
	private final LocalDateTime auctionEndDate;

	public AuctionOverDateResponse(Long auctionItemId, LocalDateTime auctionStartDate, LocalDateTime auctionEndDate) {
		this.auctionItemId = auctionItemId;
		this.auctionStartDate = auctionStartDate;
		this.auctionEndDate = auctionEndDate;
	}
}
