package com.encore.auction.controller.bidding.bidding.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public final class BiddingUpdateRequest {

	@NotEmpty
	private final String userId;

	@NotNull
	private final Long amount;

	public BiddingUpdateRequest(String userId, Long amount) {
		this.userId = userId;
		this.amount = amount;
	}
}
