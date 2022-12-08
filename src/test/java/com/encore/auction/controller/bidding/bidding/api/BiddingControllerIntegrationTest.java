package com.encore.auction.controller.bidding.bidding.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.controller.bidding.bidding.requests.BiddingRegisterRequest;
import com.encore.auction.controller.bidding.bidding.requests.BiddingUpdateRequest;
import com.encore.auction.controller.bidding.bidding.responses.BiddingDeleteResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingIdResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingRetrieveResponse;
import com.encore.auction.exception.WrongRequestException;

@SpringBootTest
@Transactional
class BiddingControllerIntegrationTest {

	@Autowired
	private BiddingController biddingController;

	private final String userId = "tester1";
	private final Long auctionItemId = 15L;
	private final Long amount = 1600000000L;
	private final Long alreadyBiddingAuctionItemId = 1L;
	private final Long biddingId = 1L;
	private final Long updateAmount = 1121211211L;

	@Test
	@DisplayName("Register Bidding Integration Controller Test - Success")
	void registerBiddingIntegrationSuccess() {
		//given
		BiddingRegisterRequest biddingRegisterRequest = new BiddingRegisterRequest(userId, auctionItemId, amount);
		//when
		ResponseEntity<BiddingIdResponse> biddingIdResponse = biddingController.registerBidding(biddingRegisterRequest);
		//then
		assertNotNull(biddingIdResponse.getBody().getBiddingId());
	}

	@Test
	@DisplayName("Register Bidding Integration Controller Test - Bidding already exist Fail")
	void registerBiddingIntegrationFail() {
		//given
		BiddingRegisterRequest biddingRegisterRequest = new BiddingRegisterRequest(userId, alreadyBiddingAuctionItemId,
			amount);
		//when //then
		assertThrows(WrongRequestException.class, () -> biddingController.registerBidding(biddingRegisterRequest));
	}

	@Test
	@DisplayName("Retrieve Bidding Integration Controller Test - Success")
	void retrieveBiddingIntegrationSuccess() {
		//given //when
		ResponseEntity<BiddingRetrieveResponse> biddingRetrieveResponse = biddingController.retrieveBidding(biddingId);
		//then
		assertNotNull(biddingRetrieveResponse.getBody().getBiddingId());
	}

	@Test
	@DisplayName("Update Bidding Integration Controller Test - Success")
	void updateBiddingIntegrationSuccess() {
		//given
		BiddingUpdateRequest biddingUpdateRequest = new BiddingUpdateRequest(userId, updateAmount);
		//when
		ResponseEntity<BiddingDetailsResponse> biddingDetailsResponse = biddingController.updateBidding(biddingId,
			biddingUpdateRequest);
		//then
		assertNotNull(biddingDetailsResponse.getBody().getBiddingId());
	}

	@Test
	@DisplayName("Delete Bidding Integration Controller Test - Success")
	void deleteBiddingIntegrationSuccess() {
		//given //when
		ResponseEntity<BiddingDeleteResponse> biddingDeleteResponse = biddingController.deleteBidding(biddingId,
			userId);
		//then
		assertTrue(biddingDeleteResponse.getBody().isState());
	}
}
