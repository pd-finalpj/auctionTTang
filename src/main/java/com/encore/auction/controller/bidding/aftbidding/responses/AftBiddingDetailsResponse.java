package com.encore.auction.controller.bidding.aftbidding.responses;

import java.time.LocalDateTime;

import com.encore.auction.model.bidding.aftbidding.BiddingResult;
import com.encore.auction.utils.validator.LocalDateTimeValidator;

import lombok.Getter;

@Getter
public final class AftBiddingDetailsResponse {

	private final Long aftBiddingId;

	private final Long biddingId;

	private final Long auctionItemId;

	private final String auctionItemName;

	private final String biddingDate;

	private final Long amount;

	private final String decideDate;

	private final BiddingResult biddingResult;

	public AftBiddingDetailsResponse(Long aftBiddingId, Long biddingId, Long auctionItemId, String auctionItemName,
		LocalDateTime biddingDate, Long amount, LocalDateTime decideDate, BiddingResult biddingResult) {
		this.aftBiddingId = aftBiddingId;
		this.biddingId = biddingId;
		this.auctionItemId = auctionItemId;
		this.auctionItemName = auctionItemName;
		this.biddingDate = LocalDateTimeValidator.of().convertLocalDateTimeToString(biddingDate);
		this.amount = amount;
		this.decideDate = LocalDateTimeValidator.of().convertLocalDateTimeToString(decideDate);
		this.biddingResult = biddingResult;
	}
}
