package com.encore.auction.model.auction.failed.log;

import org.springframework.data.mongodb.core.mapping.Document;

import com.encore.auction.controller.auction.responses.AuctionDetailsResponse;

@Document(collection = "auction_failed_details")
public class AuctionFailedLogDetails {

	private Long failedLogId;

	private AuctionDetailsResponse auctionDetailsResponse;

	public AuctionFailedLogDetails(Long failedLogId, AuctionDetailsResponse auctionDetailsResponse) {
		this.failedLogId = failedLogId;
		this.auctionDetailsResponse = auctionDetailsResponse;
	}
}
