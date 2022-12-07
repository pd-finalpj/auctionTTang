package com.encore.auction.controller.auction.requests;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.encore.auction.model.auction.item.ItemCategory;

import lombok.Getter;

@Getter
public class AuctionCreateRequest {

	@NotEmpty
	private final String managerId;

	@NotEmpty
	private final String addressCode;

	@NotEmpty
	private final String auctionItemName;

	@NotEmpty
	private final String location;

	@NotEmpty
	private final String lotNumber;

	@NotEmpty
	private final String addressDetail;

	@NotNull
	private final Long appraisedValue;

	@NotNull
	private final LocalDateTime auctionStartDate;

	@NotNull
	private final LocalDateTime auctionEndDate;

	@NotNull
	private final ItemCategory itemCategory;

	@NotNull
	private final Double areaSize;

	public AuctionCreateRequest(String managerId, String addressCode, String auctionItemName, String location,
		String lotNumber, String addressDetail, Long appraisedValue, LocalDateTime auctionStartDate,
		LocalDateTime auctionEndDate,
		ItemCategory itemCategory, Double areaSize) {
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
