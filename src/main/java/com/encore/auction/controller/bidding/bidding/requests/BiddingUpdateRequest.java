package com.encore.auction.controller.bidding.bidding.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public final class BiddingUpdateRequest {

	@NotEmpty
	@Pattern(regexp = "^[0-9A-Za-z]{2,12}$")
	private final String userId;

	@NotNull
	private final Long amount;

	public BiddingUpdateRequest(String userId, Long amount) {
		this.userId = userId;
		this.amount = amount;
	}
}
