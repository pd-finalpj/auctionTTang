package com.encore.auction.controller.auction.responses;

import java.time.LocalDateTime;
import java.util.List;

import com.encore.auction.controller.comment.responses.CommentDetailsResponse;
import com.encore.auction.model.auction.item.ItemCategory;

import lombok.Getter;

@Getter
public final class AuctionRetrieveResponse {

	private final long auctionItemId;

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

	private final Integer auctionFailedCount;

	private final Integer hit;

	private final List<CommentDetailsResponse> commentDetailsResponseList;

	public AuctionRetrieveResponse(long auctionItemId, String managerId, String addressCode, String auctionItemName,
		String location, String lotNumber, String addressDetail, Long appraisedValue, LocalDateTime auctionStartDate,
		LocalDateTime auctionEndDate, ItemCategory itemCategory, Double areaSize, Integer auctionFailedCount,
		Integer hit,
		List<CommentDetailsResponse> commentDetailsResponseList) {
		this.auctionItemId = auctionItemId;
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
		this.auctionFailedCount = auctionFailedCount;
		this.hit = hit;
		this.commentDetailsResponseList = commentDetailsResponseList;
	}
}
