package com.encore.auction.controller.auction.requests;

import java.time.LocalDateTime;

import com.encore.auction.model.auction.item.ItemCategory;

import lombok.Getter;

@Getter
public class AuctionUpdateRequest {

	private final String managerId;

	private final String addressCode;

	private final String auctionItemName;

	private final String location;

	private final String lotNumber;

	private final String addressDetail;

	private final Long appraisedValue;

	private final LocalDateTime auctionStartDate;

	private final LocalDateTime auctionEndDate;

	private final ItemCategory itemCategory;

	private final Double areaSize;

	public AuctionUpdateRequest(String managerId, String addressCode, String auctionItemName,
		String location, String lotNumber, String addressDetail, Long appraisedValue, LocalDateTime auctionStartDate,
		LocalDateTime auctionEndDate, ItemCategory itemCategory, Double areaSize) {
		this.managerId = managerId;
		this.addressCode = addressCode;
		this.auctionItemName = auctionItemName;
		this.location = location;
		this.lotNumber = lotNumber;
		this.addressDetail = addressDetail;
		this.appraisedValue = appraisedValue;
		this.auctionStartDate = auctionStartDate;
		this.auctionEndDate = auctionEndDate;
		this.itemCategory = itemCategory;
		this.areaSize = areaSize;
	}
}
