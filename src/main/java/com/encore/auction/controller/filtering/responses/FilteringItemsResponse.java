package com.encore.auction.controller.filtering.responses;

import java.time.LocalDateTime;

import com.encore.auction.model.auction.item.ItemCategory;

import lombok.Getter;

@Getter
public class FilteringItemsResponse {

	private final Long auctionItemId;

	private final String managerId;

	private final String stateName;

	private final String auctionItemName;

	private final String location;

	private final String lotNumber;

	private final String addressDetail;

	private final Long appraisedValue;

	private final LocalDateTime auctionStartDate;

	private final LocalDateTime auctionEndDate;

	private final ItemCategory itemCategory;

	private final Double areaSize;

	private final Integer auctionFailedCount;

	private final Integer hit;

	private final Boolean state;

	public FilteringItemsResponse(Long auctionItemId, String managerId, String stateName, String auctionItemName,
		String location, String lotNumber, String addressDetail, Long appraisedValue, LocalDateTime auctionStartDate,
		LocalDateTime auctionEndDate, ItemCategory itemCategory, Double areaSize, Integer auctionFailedCount,
		Integer hit,
		Boolean state) {
		this.auctionItemId = auctionItemId;
		this.managerId = managerId;
		this.stateName = stateName;
		this.auctionItemName = auctionItemName;
		this.location = location;
		this.lotNumber = lotNumber;
		this.addressDetail = addressDetail;
		this.appraisedValue = appraisedValue;
		this.auctionStartDate = auctionStartDate;
		this.auctionEndDate = auctionEndDate;
		this.itemCategory = itemCategory;
		this.areaSize = areaSize;
		this.auctionFailedCount = auctionFailedCount;
		this.hit = hit;
		this.state = state;
	}
}
