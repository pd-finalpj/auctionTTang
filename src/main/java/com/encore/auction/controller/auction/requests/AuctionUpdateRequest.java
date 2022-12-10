package com.encore.auction.controller.auction.requests;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.encore.auction.model.auction.item.ItemCategory;

import lombok.Getter;

@Getter
public class AuctionUpdateRequest {

	@NotEmpty
	@Pattern(regexp = "^[A-Za-z0-9]{2,12}$", message = "아이디는 2~12자로 영문 대소문자, 숫자만 사용할 수 있습니다.")
	private final String managerId;

	@NotEmpty
	@Size(max = 5)
	private final String addressCode;
	@NotEmpty
	@Size(max = 100)
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

	public AuctionUpdateRequest(String managerId, String addressCode, String auctionItemName, String location,
		String lotNumber, String addressDetail, Long appraisedValue, LocalDateTime auctionStartDate,
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
