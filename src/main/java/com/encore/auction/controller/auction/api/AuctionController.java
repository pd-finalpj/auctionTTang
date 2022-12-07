package com.encore.auction.controller.auction.api;

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

import com.encore.auction.controller.auction.requests.AuctionCreateRequest;
import com.encore.auction.controller.auction.requests.AuctionUpdateRequest;
import com.encore.auction.controller.auction.responses.AuctionDeleteResponse;
import com.encore.auction.controller.auction.responses.AuctionDetailsResponse;
import com.encore.auction.controller.auction.responses.AuctionIdResponse;
import com.encore.auction.service.auction.AuctionService;

@RestController
@RequestMapping("/auction")
public class AuctionController {

	private final AuctionService auctionService;

	public AuctionController(AuctionService auctionService) {
		this.auctionService = auctionService;
	}

	@PostMapping("/create")
	public ResponseEntity<AuctionIdResponse> createAuctionItem(
		@Valid @RequestBody AuctionCreateRequest auctionCreateRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(auctionService.createAuctionItem(auctionCreateRequest));
	}

	@GetMapping("/{auction-item-id}")
	public ResponseEntity<AuctionDetailsResponse> retrieveAuctionItem(
		@PathVariable("auction-item-id") Long auctionItemId) {
		return ResponseEntity.ok().body(auctionService.retrieveAuctionItem(auctionItemId));
	}

	@PutMapping("/{auction-item-id}")
	public ResponseEntity<AuctionDetailsResponse> updateAuctionItem(
		@PathVariable("auction-item-id") Long auctionItemId, @RequestBody AuctionUpdateRequest auctionUpdateRequest) {
		return ResponseEntity.ok().body(auctionService.updateAuctionItem(auctionItemId, auctionUpdateRequest));
	}

	@DeleteMapping("/{auction-item-id}")
	public ResponseEntity<AuctionDeleteResponse> deleteAuctionItem(@PathVariable("auction-item-id") Long auctionItemId,
		@RequestHeader("managerId") String managerId) {
		return ResponseEntity.ok().body(auctionService.deleteAuctionItem(auctionItemId, managerId));
	}
}
