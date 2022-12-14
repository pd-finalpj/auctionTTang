package com.encore.auction.controller.filtering.responses;

import java.util.List;

import lombok.Getter;

@Getter
public class FilteringItemsListResponse {

	private final Integer pageNum;
	private final Boolean hasNext;
	private final List<FilteringItemsResponse> filteringItemsResponseList;

	public FilteringItemsListResponse(Integer pageNum, Boolean hasNext,
		List<FilteringItemsResponse> filteringItemsResponseList) {
		this.pageNum = pageNum;
		this.hasNext = hasNext;
		this.filteringItemsResponseList = filteringItemsResponseList;
	}
}
