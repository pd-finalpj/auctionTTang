package com.encore.auction.repository.bidding.aftbidding;

import java.util.List;

import com.encore.auction.controller.bidding.aftbidding.responses.AftBiddingDetailsResponse;

public interface AftBiddingRetrieveRepository {

	public List<AftBiddingDetailsResponse> retrieveAftBiddingListByUserId(String userId);
}
