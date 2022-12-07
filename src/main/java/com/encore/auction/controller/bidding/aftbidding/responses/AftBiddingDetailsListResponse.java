package com.encore.auction.controller.bidding.aftbidding.responses;

import java.util.List;

import lombok.Getter;

@Getter
public final class AftBiddingDetailsListResponse {

	private final String userId;
	private final List<AftBiddingDetailsResponse> aftBiddingDetailsResponseList;

	public AftBiddingDetailsListResponse(String userId, List<AftBiddingDetailsResponse> aftBiddingDetailsResponseList) {
		this.userId = userId;
		this.aftBiddingDetailsResponseList = aftBiddingDetailsResponseList;
	}
}
