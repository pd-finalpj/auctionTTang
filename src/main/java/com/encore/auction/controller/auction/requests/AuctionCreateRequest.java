package com.encore.auction.controller.auction.requests;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.encore.auction.model.auction.item.ItemCategory;

import lombok.Getter;

@Getter
public class AuctionCreateRequest {

	@NotEmpty
	@Size(max = 5)
	private final String addressCode;

	@NotEmpty
	private final String auctionItemCaseNumber;

	@NotEmpty
	private final String auctionItemName;

	@NotEmpty
	@Size(max = 10)
	private final String location;

	@NotEmpty
	@Size(max = 8)
	private final String lotNumber;

	@NotEmpty
	@Size(max = 50)
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

	public AuctionCreateRequest(String addressCode, String auctionItemCaseNumber, String auctionItemName,
		String location,
		String lotNumber, String addressDetail, Long appraisedValue, LocalDateTime auctionStartDate,
		LocalDateTime auctionEndDate, ItemCategory itemCategory, Double areaSize) {
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
	}
}
