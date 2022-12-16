package com.encore.auction.model.biddingdetails;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.encore.auction.controller.auction.responses.AuctionDetailsResponse;
import com.encore.auction.model.bidding.aftbidding.BiddingResult;

@Document(collection = "bidding_details")
public class BiddingDetails {

	private Long biddingId;

	private String userId;

	private String userBirth;

	private LocalDateTime biddingDate;

	private Long amount;

	private LocalDateTime decideDate;

	private BiddingResult biddingResult;

	private AuctionDetailsResponse auctionDetailsResponse;

	public BiddingDetails(Long biddingId, String userId, String userBirth, LocalDateTime biddingDate, Long amount,
		LocalDateTime decideDate, BiddingResult biddingResult, AuctionDetailsResponse auctionDetailsResponse) {
		this.biddingId = biddingId;
		this.userId = userId;
		this.userBirth = userBirth;
		this.biddingDate = biddingDate;
		this.amount = amount;
		this.decideDate = decideDate;
		this.biddingResult = biddingResult;
		this.auctionDetailsResponse = auctionDetailsResponse;
	}
}
