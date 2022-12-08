package com.encore.auction.controller.filtering.responses;

import java.util.List;

import lombok.Getter;

@Getter
public class FilteringItemsListResponse {

	private final List<FilteringItemsResponse> filteringItemsResponseList;

	public FilteringItemsListResponse(List<FilteringItemsResponse> filteringItemsResponseList) {
		this.filteringItemsResponseList = filteringItemsResponseList;
	}

}
