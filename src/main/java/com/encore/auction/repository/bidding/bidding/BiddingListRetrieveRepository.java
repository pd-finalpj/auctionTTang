package com.encore.auction.repository.bidding.bidding;

import java.util.List;

import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsResponse;

public interface BiddingListRetrieveRepository {

	public List<BiddingDetailsResponse> retrieveBiddingListByUserId(String userId);
}
