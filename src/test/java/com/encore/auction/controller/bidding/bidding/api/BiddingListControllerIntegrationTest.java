// package com.encore.auction.controller.bidding.bidding.api;
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
// import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsListResponse;
//
// @SpringBootTest
// @Transactional
// class BiddingListControllerIntegrationTest {
//
// 	@Autowired
// 	private BiddingListController biddingListController;
//
// 	private final String userId = "tester1";
// 	private final String biddingDoesNotExistUserId = "jji0428";
//
// 	@Test
// 	@DisplayName("Retrieve Bidding List By UserId Integration Test - Success")
// 	void retrieveBiddingListByUserIdSuccess() {
// 		//given //when
// 		ResponseEntity<BiddingDetailsListResponse> biddingDetailsListResponse = biddingListController.retrieveBiddingListByUserId(
// 			userId);
// 		assertTrue(0 < biddingDetailsListResponse.getBody().getBiddingDetailsResponseList().size());
// 	}
//
// 	@Test
// 	@DisplayName("Retrieve Bidding List By UserId Bidding Does Not Exist Integration Test - Success")
// 	void retrieveBiddingListByUserIdBiddingDoesNotExistSuccess() {
// 		//given //when
// 		ResponseEntity<BiddingDetailsListResponse> biddingDetailsListResponse = biddingListController.retrieveBiddingListByUserId(
// 			biddingDoesNotExistUserId);
// 		assertEquals(0, biddingDetailsListResponse.getBody().getBiddingDetailsResponseList().size());
// 	}
// }
