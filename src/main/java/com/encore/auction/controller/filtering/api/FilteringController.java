package com.encore.auction.controller.filtering.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.filtering.responses.FilteringItemsListResponse;
import com.encore.auction.service.filtering.FilteringService;

@RestController
@RequestMapping("/auction/get/list")
public class FilteringController {

	private final FilteringService filteringService;

	public FilteringController(FilteringService filteringService) {
		this.filteringService = filteringService;
	}

	@GetMapping
	public ResponseEntity<FilteringItemsListResponse> getAuctionItemList() {
		return ResponseEntity.ok().body(filteringService.getAuctionItemList());
	}

	@GetMapping("/{address}/{end-date}/{item-category}/{auction-failed-count}")
	public ResponseEntity<FilteringItemsListResponse> getFilteredAuctionItemList(
		@PathVariable("address") String address, @PathVariable("end-date") String date,
		@PathVariable("item-category") String category,
		@PathVariable("auction-failed-count") Integer auctionFailedCount) {
		return ResponseEntity.ok()
			.body(filteringService.getFilteredAuctionItemList(address, date, category, auctionFailedCount));
	}

}
