package com.encore.auction.controller.auction.responses;

import java.util.List;

import lombok.Getter;

@Getter
public final class AuctionFailedLogListResponse {

	private final List<AuctionFailedLogDetailsResponse> auctionFailedLogDetailsResponseList;

	public AuctionFailedLogListResponse(List<AuctionFailedLogDetailsResponse> auctionFailedLogDetailsResponseList) {
		this.auctionFailedLogDetailsResponseList = auctionFailedLogDetailsResponseList;
	}
}
