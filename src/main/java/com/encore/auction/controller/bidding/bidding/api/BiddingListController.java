package com.encore.auction.controller.bidding.bidding.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsListResponse;
import com.encore.auction.service.bidding.bidding.BiddingListService;

@RestController
@RequestMapping("/bidding-list")
public class BiddingListController {

	private final BiddingListService biddingListService;

	public BiddingListController(BiddingListService biddingListService) {
		this.biddingListService = biddingListService;
	}

	@GetMapping("/{user-id}")
	public ResponseEntity<BiddingDetailsListResponse> retrieveBiddingListByUserId(
		@PathVariable("user-id") String userId) {
		return ResponseEntity.ok().body(biddingListService.retrieveBiddingListByUserId(userId));
	}
}
