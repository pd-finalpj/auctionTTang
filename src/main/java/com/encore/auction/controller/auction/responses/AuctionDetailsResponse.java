package com.encore.auction.controller.auction.responses;

import java.time.LocalDateTime;

import com.encore.auction.model.auction.item.ItemCategory;
import com.encore.auction.model.auction.item.ItemSoldState;

import lombok.Getter;

@Getter
public class AuctionDetailsResponse {

	private final long auctionItemId;

	private final String managerId;

	private final String addressCode;

	private final String auctionItemCaseNumber;

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

	private final ItemSoldState itemSoldState;

	private final Integer bookmarkCount;

	private final Integer hit;

	public AuctionDetailsResponse(long auctionItemId, String managerId, String addressCode,
		String auctionItemCaseNumber,
		String auctionItemName, String location, String lotNumber, String addressDetail, Long appraisedValue,
		LocalDateTime auctionStartDate, LocalDateTime auctionEndDate, ItemCategory itemCategory, Double areaSize,
		Integer auctionFailedCount, ItemSoldState itemSoldState, Integer bookmarkCount, Integer hit) {
		this.auctionItemId = auctionItemId;
		this.managerId = managerId;
		this.addressCode = addressCode;
		this.auctionItemCaseNumber = auctionItemCaseNumber;
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
		this.itemSoldState = itemSoldState;
		this.bookmarkCount = bookmarkCount;
		this.hit = hit;
	}
}
