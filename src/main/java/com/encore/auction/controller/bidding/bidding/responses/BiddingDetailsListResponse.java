package com.encore.auction.controller.bidding.bidding.responses;

import java.util.List;

import lombok.Getter;

@Getter
public final class BiddingDetailsListResponse {

	private final String userId;
	private final List<BiddingDetailsResponse> biddingDetailsResponseList;

	public BiddingDetailsListResponse(String userId, List<BiddingDetailsResponse> biddingDetailsResponseList) {
		this.userId = userId;
		this.biddingDetailsResponseList = biddingDetailsResponseList;
	}
}
