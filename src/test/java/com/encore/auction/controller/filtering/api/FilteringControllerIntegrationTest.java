// package com.encore.auction.controller.filtering.api;
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
// import com.encore.auction.controller.filtering.responses.FilteringItemsListResponse;
//
// @SpringBootTest
// @Transactional
// public class FilteringControllerIntegrationTest {
//
// 	@Autowired
// 	private FilteringController filteringController;
//
// 	private String state = "서울특별시";
//
// 	private String auctionEndDate = "2023-10-24 17:00:00";
//
// 	private String auctionEndDate2 = null;
//
// 	private String category = "APARTMENT";
//
// 	private Integer auctionFailedCount = 5;
//
// 	@Test
// 	@DisplayName("Get AuctionItemList Integration Controller Test - Success")
// 	void getAuctionItemListSuccess() {
// 		//given //when
// 		ResponseEntity<FilteringItemsListResponse> filteringItemsListResponse = filteringController.getAuctionItemList();
// 		//then
// 		assertTrue(0 < filteringItemsListResponse.getBody().getFilteringItemsResponseList().size());
// 	}
//
// 	@Test
// 	@DisplayName("Get Filtered AuctionItemList Integration Controller Test - Success")
// 	void getFilteredAuctionItemListSuccess() {
// 		//given //when
// 		ResponseEntity<FilteringItemsListResponse> filteringItemsListResponse = filteringController.getFilteredAuctionItemList(
// 			state, auctionEndDate2, category, auctionFailedCount);
// 		//then
// 		assertTrue(0 < filteringItemsListResponse.getBody().getFilteringItemsResponseList().size());
// 	}
// }
