package com.encore.auction.controller.bidding.bidding.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsListResponse;
import com.encore.auction.service.bidding.bidding.BiddingListService;
import com.encore.auction.utils.security.Permission;

@RestController
@RequestMapping("/api/bidding-list")
public class BiddingListController {

	private final BiddingListService biddingListService;

	public BiddingListController(BiddingListService biddingListService) {
		this.biddingListService = biddingListService;
	}

	@Permission
	@GetMapping
	public ResponseEntity<BiddingDetailsListResponse> retrieveBiddingListByUserId(
		@RequestHeader("Token") String token) {
		return ResponseEntity.ok().body(biddingListService.retrieveBiddingListByUserId(token));
	}
}
