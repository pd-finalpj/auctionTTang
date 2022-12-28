package com.encore.auction.controller.bidding.bidding.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.bidding.bidding.requests.BiddingRegisterRequest;
import com.encore.auction.controller.bidding.bidding.requests.BiddingUpdateRequest;
import com.encore.auction.controller.bidding.bidding.responses.BiddingDeleteResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingIdResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingRetrieveResponse;
import com.encore.auction.service.bidding.bidding.BiddingService;
import com.encore.auction.utils.security.Permission;

@RestController
@RequestMapping("/api/bidding")
public class BiddingController {

	private final BiddingService biddingService;

	public BiddingController(BiddingService biddingService) {
		this.biddingService = biddingService;
	}

	@Permission
	@PostMapping
	public ResponseEntity<BiddingIdResponse> registerBidding(
		@Valid @RequestBody BiddingRegisterRequest biddingRegisterRequest, @RequestHeader("Token") String token) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(biddingService.registerBidding(biddingRegisterRequest, token));
	}

	@GetMapping("/{bidding-id}")
	public ResponseEntity<BiddingRetrieveResponse> retrieveBidding(@PathVariable("bidding-id") Long biddingId) {
		return ResponseEntity.ok().body(biddingService.retrieveBidding(biddingId));
	}

	@Permission
	@PutMapping("/{bidding-id}")
	public ResponseEntity<BiddingDetailsResponse> updateBidding(@PathVariable("bidding-id") Long biddingId,
		@Valid @RequestBody
		BiddingUpdateRequest biddingUpdateRequest, @RequestHeader("Token") String token) {
		return ResponseEntity.ok().body(biddingService.updateBidding(biddingId, biddingUpdateRequest, token));
	}

	@Permission
	@DeleteMapping("/{bidding-id}")
	public ResponseEntity<BiddingDeleteResponse> deleteBidding(@PathVariable("bidding-id") Long biddingId,
		@RequestHeader("Token") String token) {
		return ResponseEntity.ok().body(biddingService.deleteBidding(biddingId, token));
	}
}
