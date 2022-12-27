package com.encore.auction.controller.bidding.bidding.responses;

import lombok.Getter;

@Getter
public final class BiddingIdResponse {

	private final Long biddingId;

	public BiddingIdResponse(Long biddingId) {
		this.biddingId = biddingId;
	}
}
