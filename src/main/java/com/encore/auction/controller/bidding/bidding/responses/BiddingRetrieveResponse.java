package com.encore.auction.controller.bidding.bidding.responses;

import java.time.LocalDateTime;

import com.encore.auction.model.bidding.aftbidding.BiddingResult;
import com.encore.auction.utils.validator.LocalDateTimeValidator;

import lombok.Getter;

@Getter
public class BiddingRetrieveResponse {

	private final Long biddingId;

	private final Long auctionItemId;

	private final String auctionItemName;

	private final String biddingDate;

	private final Long amount;

	private final String decideDate;

	private final BiddingResult biddingResult;

	public BiddingRetrieveResponse(Long biddingId, Long auctionItemId, String auctionItemName,
		LocalDateTime biddingDate,
		Long amount, LocalDateTime decideDate, BiddingResult biddingResult) {
		this.biddingId = biddingId;
		this.auctionItemId = auctionItemId;
		this.auctionItemName = auctionItemName;
		this.biddingDate = LocalDateTimeValidator.of().convertLocalDateTimeToString(biddingDate);
		this.amount = amount;
		this.decideDate =
			decideDate == null ? "UNDEFINED" :
				LocalDateTimeValidator.of().convertLocalDateTimeToString(decideDate);
		this.biddingResult = biddingResult == null ? BiddingResult.UNDEFINED : biddingResult;
	}
}
