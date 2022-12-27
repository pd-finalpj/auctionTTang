package com.encore.auction.utils.mapper;

import org.springframework.data.domain.Slice;

import com.encore.auction.controller.filtering.responses.FilteringItemsListResponse;
import com.encore.auction.controller.filtering.responses.FilteringItemsResponse;
import com.encore.auction.model.filtering.Filtering;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilteringMapper {

	private static FilteringMapper filteringMapper = null;

	public static FilteringMapper of() {
		if (filteringMapper == null) {
			filteringMapper = new FilteringMapper();
		}
		return filteringMapper;
	}

	public FilteringItemsListResponse filteringToResponse(Slice<FilteringItemsResponse> filteringAuctionItemList) {
		return new FilteringItemsListResponse(filteringAuctionItemList.getNumber() + 1,
			filteringAuctionItemList.hasNext(),
			filteringAuctionItemList.getContent());
	}

	public FilteringItemsListResponse redisFilteringToResponse(Filtering filtering) {
		return new FilteringItemsListResponse(filtering.getPageNum(), filtering.getHasNext(),
			filtering.getFilteringItemsResponseList());
	}

	public Filtering filteringToRedisFiltering(String redisId, Slice<FilteringItemsResponse> filteringItemsResponses) {
		return new Filtering(redisId, filteringItemsResponses.getNumber() + 1, filteringItemsResponses.hasNext(),
			filteringItemsResponses.getContent());
	}
}
