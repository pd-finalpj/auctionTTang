package com.encore.auction.repository;

import java.util.List;

import com.encore.auction.controller.filtering.responses.FilteringItemsResponse;

public interface FilteringAuctionItemListRepository {

	public List<FilteringItemsResponse> filteringAuctionItemList(String address, String date, String category,
		Integer auctionFailedCount);

}
