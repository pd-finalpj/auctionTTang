package com.encore.auction.model.filtering;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

import com.encore.auction.controller.filtering.responses.FilteringItemsResponse;

import lombok.Getter;

@Getter
@RedisHash(value = "filtering", timeToLive = 3000)
public class Filtering {

	@Id
	private String id;
	private Integer pageNum;
	private Boolean hasNext;
	private List<FilteringItemsResponse> filteringItemsResponseList;

	public Filtering(String id, Integer pageNum, Boolean hasNext,
		List<FilteringItemsResponse> filteringItemsResponseList) {
		this.id = id;
		this.pageNum = pageNum;
		this.hasNext = hasNext;
		this.filteringItemsResponseList = filteringItemsResponseList;
	}
}
