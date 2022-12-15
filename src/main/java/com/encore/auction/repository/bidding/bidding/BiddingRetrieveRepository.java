package com.encore.auction.repository.bidding.bidding;

import com.encore.auction.controller.bidding.bidding.responses.BiddingRetrieveResponse;

public interface BiddingRetrieveRepository {

	public BiddingRetrieveResponse retrieveBiddingByBiddingId(Long biddingId);
}
