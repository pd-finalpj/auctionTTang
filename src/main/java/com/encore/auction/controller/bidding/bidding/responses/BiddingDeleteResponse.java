package com.encore.auction.controller.bidding.bidding.responses;

import lombok.Getter;

@Getter
public final class BiddingDeleteResponse {

	private final Long biddingId;
	private final boolean state;

	public BiddingDeleteResponse(Long biddingId, boolean state) {
		this.biddingId = biddingId;
		this.state = state;
	}
}
