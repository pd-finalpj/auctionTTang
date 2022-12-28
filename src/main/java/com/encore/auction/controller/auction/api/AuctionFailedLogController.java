package com.encore.auction.controller.auction.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.auction.responses.AuctionFailedLogListResponse;
import com.encore.auction.service.auction.AuctionFailedLogService;

@RestController
@RequestMapping("/api/auction-failed-log")
public class AuctionFailedLogController {

	private final AuctionFailedLogService auctionFailedLogService;

	public AuctionFailedLogController(AuctionFailedLogService auctionFailedLogService) {
		this.auctionFailedLogService = auctionFailedLogService;
	}

	@GetMapping("/{auction-item-id}")
	public ResponseEntity<AuctionFailedLogListResponse> getAuctionFailedLogList(
		@PathVariable("auction-item-id") Long auctionItemId) {
		return ResponseEntity.ok().body(auctionFailedLogService.getAuctionFailedLogListByAuctionItemId(auctionItemId));
	}
}
