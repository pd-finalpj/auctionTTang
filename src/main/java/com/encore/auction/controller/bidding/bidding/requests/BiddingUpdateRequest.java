package com.encore.auction.controller.bidding.bidding.requests;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public final class BiddingUpdateRequest {

	@NotNull
	private final Long amount;

	public BiddingUpdateRequest(Long amount) {
		this.amount = amount;
	}
}
