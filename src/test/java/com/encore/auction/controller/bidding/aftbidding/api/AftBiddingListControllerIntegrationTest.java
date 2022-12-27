// package com.encore.auction.controller.bidding.aftbidding.api;
//
// import static org.junit.jupiter.api.Assertions.*;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.ResponseEntity;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.encore.auction.controller.bidding.aftbidding.responses.AftBiddingDetailsListResponse;
//
// @SpringBootTest
// @Transactional
// class AftBiddingListControllerIntegrationTest {
//
// 	@Autowired
// 	private AftBiddingListController biddingListController;
//
// 	private final String userId = "tester1";
// 	private final String aftBiddingDoesNotExistUserId = "jji0428";
//
// 	@Test
// 	@DisplayName("Retrieve Aft Bidding List By UserId Integration Test - Success")
// 	void retrieveAftBiddingListByUserIdSuccess() {
// 		//given //when
// 		ResponseEntity<AftBiddingDetailsListResponse> aftBiddingDetailsListResponse = biddingListController.retrieveAftBiddingListByUserId(
// 			userId);
// 		//then
// 		assertTrue(0 < aftBiddingDetailsListResponse.getBody().getAftBiddingDetailsResponseList().size());
// 	}
//
// 	@Test
// 	@DisplayName("Retrieve Aft Bidding List By UserId AftBidding Does Not Exist Integration Test - Success")
// 	void retrieveAftBiddingListByUserIdAftBiddingDoesNotExistSuccess() {
// 		//given //when
// 		ResponseEntity<AftBiddingDetailsListResponse> aftBiddingDetailsListResponse = biddingListController.retrieveAftBiddingListByUserId(
// 			aftBiddingDoesNotExistUserId);
// 		//then
// 		assertEquals(0, aftBiddingDetailsListResponse.getBody().getAftBiddingDetailsResponseList().size());
// 	}
// }
