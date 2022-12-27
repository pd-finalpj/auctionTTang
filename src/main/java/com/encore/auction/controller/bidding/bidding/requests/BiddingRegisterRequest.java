package com.encore.auction.controller.bidding.bidding.requests;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public final class BiddingRegisterRequest {

	@NotNull
	private final Long auctionItemId;

	@NotNull
	private final Long amount;

	public BiddingRegisterRequest(Long auctionItemId, Long amount) {
		this.auctionItemId = auctionItemId;
		this.amount = amount;
	}
}
