package com.encore.auction.controller.auction.responses;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public final class AuctionFailedLogDetailsResponse {

	private final Long auctionItemId;

	private final LocalDateTime auctionStartDate;

	private final LocalDateTime auctionEndDate;

	private final Long appraisedValue;

	public AuctionFailedLogDetailsResponse(Long auctionItemId, LocalDateTime auctionStartDate,
		LocalDateTime auctionEndDate,
		Long appraisedValue) {
		this.auctionItemId = auctionItemId;
		this.auctionStartDate = auctionStartDate;
		this.auctionEndDate = auctionEndDate;
		this.appraisedValue = appraisedValue;
	}
}
