package com.encore.auction.controller.filtering.responses;

import java.util.List;

import lombok.Getter;

@Getter
public final class FilteringItemsListByManagerIdResponse {

	private final List<FilteringItemsResponse> filteringItemsResponseList;

	public FilteringItemsListByManagerIdResponse(List<FilteringItemsResponse> filteringItemsResponseList) {
		this.filteringItemsResponseList = filteringItemsResponseList;
	}
}
