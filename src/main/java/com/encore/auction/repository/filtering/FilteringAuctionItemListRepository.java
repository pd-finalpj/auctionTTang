package com.encore.auction.repository.filtering;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.encore.auction.controller.filtering.requests.FilteringAuctionItemRequest;
import com.encore.auction.controller.filtering.responses.FilteringItemsResponse;

public interface FilteringAuctionItemListRepository {

	public Slice<FilteringItemsResponse> filteringAuctionItemList(
		FilteringAuctionItemRequest filteringAuctionItemRequest, Pageable pageable);

	public List<FilteringItemsResponse> filteringAuctionItemListByManagerId(String managerId);
}
