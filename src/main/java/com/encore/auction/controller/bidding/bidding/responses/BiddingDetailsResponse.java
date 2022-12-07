package com.encore.auction.controller.bidding.bidding.responses;

import java.time.LocalDateTime;

import com.encore.auction.utils.validator.LocalDateTimeValidator;

import lombok.Getter;

@Getter
public final class BiddingDetailsResponse {

	private final Long biddingId;

	private final Long auctionItemId;

	private final String auctionItemName;

	private final String biddingDate;

	private final Long amount;

	public BiddingDetailsResponse(Long biddingId, Long auctionItemId, String auctionItemName, LocalDateTime biddingDate,
		Long amount) {
		this.biddingId = biddingId;
		this.auctionItemId = auctionItemId;
		this.auctionItemName = auctionItemName;
		this.biddingDate = LocalDateTimeValidator.of().convertLocalDateTimeToString(biddingDate);
		this.amount = amount;
	}
}
