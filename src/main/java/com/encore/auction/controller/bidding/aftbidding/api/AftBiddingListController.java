package com.encore.auction.controller.bidding.aftbidding.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.bidding.aftbidding.responses.AftBiddingDetailsListResponse;
import com.encore.auction.service.bidding.aftbidding.AftBiddingListService;
import com.encore.auction.utils.security.Permission;

@RestController
@RequestMapping("/api/aft-bidding-list")
public class AftBiddingListController {

	private final AftBiddingListService aftBiddingListService;

	public AftBiddingListController(AftBiddingListService aftBiddingListService) {
		this.aftBiddingListService = aftBiddingListService;
	}

	@Permission
	@GetMapping
	public ResponseEntity<AftBiddingDetailsListResponse> retrieveAftBiddingListByUserId(
		@RequestHeader("Token") String token) {
		return ResponseEntity.ok().body(aftBiddingListService.retrieveAftBiddingListByUserId(token));
	}
}
