package com.encore.auction.repository;

import com.encore.auction.controller.bidding.bidding.responses.BiddingRetrieveResponse;

public interface BiddingRetrieveRepository {

	public BiddingRetrieveResponse retrieveBiddingByBiddingId(Long biddingId);
}
