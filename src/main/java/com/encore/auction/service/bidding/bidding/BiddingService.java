package com.encore.auction.service.bidding.bidding;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.bidding.bidding.requests.BiddingRegisterRequest;
import com.encore.auction.controller.bidding.bidding.requests.BiddingUpdateRequest;
import com.encore.auction.controller.bidding.bidding.responses.BiddingDeleteResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingIdResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingRetrieveResponse;

@Service
public class BiddingService {
	public BiddingIdResponse registerBidding(BiddingRegisterRequest biddingRegisterRequest) {
		return null;
	}

	public BiddingRetrieveResponse retrieveBidding(Long biddingId) {
		return null;
	}

	public BiddingDetailsResponse updateBidding(Long biddingId, BiddingUpdateRequest biddingUpdateRequest) {
		return null;
	}

	public BiddingDeleteResponse deleteBidding(Long biddingId, String userId) {
		return null;
	}
}
