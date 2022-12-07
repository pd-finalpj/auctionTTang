package com.encore.auction.controller.bidding.aftbidding.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.bidding.aftbidding.responses.AftBiddingDetailsListResponse;
import com.encore.auction.service.bidding.aftbidding.AftBiddingListService;

@RestController
@RequestMapping("/aft-bidding-list")
public class AftBiddingListController {

	private final AftBiddingListService aftBiddingListService;

	public AftBiddingListController(AftBiddingListService aftBiddingListService) {
		this.aftBiddingListService = aftBiddingListService;
	}

	@GetMapping("/{user-id}")
	public ResponseEntity<AftBiddingDetailsListResponse> retrieveAftBiddingListByUserId(
		@PathVariable("user-id") String userId) {
		return ResponseEntity.ok().body(aftBiddingListService.retrieveAftBiddingListByUserId(userId));
	}
}
