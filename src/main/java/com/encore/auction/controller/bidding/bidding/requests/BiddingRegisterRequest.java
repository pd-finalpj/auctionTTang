package com.encore.auction.controller.bidding.bidding.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public final class BiddingRegisterRequest {

	@NotEmpty
	private final String userId;

	@NotNull
	private final Long auctionItemId;

	@NotNull
	private final Long amount;

	public BiddingRegisterRequest(String userId, Long auctionItemId, Long amount) {
		this.userId = userId;
		this.auctionItemId = auctionItemId;
		this.amount = amount;
	}
}
