package com.encore.auction.controller.auction.api;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.controller.auction.requests.AuctionCreateRequest;
import com.encore.auction.controller.auction.requests.AuctionUpdateRequest;
import com.encore.auction.controller.auction.responses.AuctionDeleteResponse;
import com.encore.auction.controller.auction.responses.AuctionDetailsResponse;
import com.encore.auction.controller.auction.responses.AuctionIdResponse;
import com.encore.auction.controller.auction.responses.AuctionRetrieveResponse;
import com.encore.auction.model.auction.item.ItemCategory;

@SpringBootTest
@Transactional
class AuctionControllerIntegrationTest {

	@Autowired
	private AuctionController auctionController;

	private final String managerId = "tester2";
	private final Long auctionItemId = 1L;
	private final String addressCode = "11010";
	private final String auctionItemName = "엔코아노트북";
	private final String location = "서울시 금천구";
	private final String lotNumber = "22";
	private final String addressDetail = "독산동";
	private final Long appraisedValue = 190000000L;
	private final LocalDateTime auctionStartDate = LocalDateTime.parse("2023-12-12T13:00:00");
	private final LocalDateTime auctionEndDate = LocalDateTime.parse("2023-12-23T13:00:00");
	private final ItemCategory itemCategory = ItemCategory.APARTMENT;
	private final Double areaSize = 123.4;

	@Test
	@DisplayName("Create Auction Item Integration Controller Test - Success")
	void createAuctionIntegrationSuccess() {
		//given
		AuctionCreateRequest auctionCreateRequest = new AuctionCreateRequest(managerId, addressCode, auctionItemName,
			location, lotNumber, addressDetail, appraisedValue, auctionStartDate, auctionEndDate, itemCategory,
			areaSize);
		//when
		ResponseEntity<AuctionIdResponse> auctionIdResponse = auctionController.createAuctionItem(auctionCreateRequest);
		//then
		assertNotNull(auctionIdResponse.getBody().getAuctionItemId());
	}

	@Test
	@DisplayName("Retrieve Auction Item Integration Controller Test - Success")
	void retrieveAuctionIntegrationSuccess() {
		//given //when
		ResponseEntity<AuctionRetrieveResponse> auctionRetrieveResponse = auctionController.retrieveAuctionItem(
			auctionItemId);
		//then
		assertNotNull(auctionRetrieveResponse.getBody().getAuctionItemId());
		assertTrue(0 < auctionRetrieveResponse.getBody().getCommentDetailsResponseList().size());
	}

	@Test
	@DisplayName("Update Auction Item Integration Controller Test - Success")
	void updateAuctionIntegrationSuccess() {
		//given
		AuctionUpdateRequest auctionUpdateRequest = new AuctionUpdateRequest(managerId, addressCode,
			auctionItemName, location, lotNumber, addressDetail, appraisedValue, auctionStartDate, auctionEndDate,
			itemCategory, areaSize);
		//when
		ResponseEntity<AuctionDetailsResponse> auctionDetailsResponse = auctionController.updateAuctionItem(
			auctionItemId,
			auctionUpdateRequest);
		//then
		assertNotNull(auctionDetailsResponse.getBody().getAuctionItemId());
	}

	@Test
	@DisplayName("delete Auction Item Integration Controller Test - Success")
	void deleteAuctionIntegrationSuccess() {
		//given //when
		ResponseEntity<AuctionDeleteResponse> auctionDeleteResponse = auctionController.deleteAuctionItem(auctionItemId,
			managerId);
		//then
		assertTrue(auctionDeleteResponse.getBody().isState());
	}
}
