package com.encore.auction.controller.auction.responses;

import java.time.LocalDateTime;
import java.util.List;

import com.encore.auction.controller.comment.responses.CommentDetailsResponse;
import com.encore.auction.controller.imagefile.responses.ImageFileUrlResponse;
import com.encore.auction.model.auction.item.ItemCategory;
import com.encore.auction.model.auction.item.ItemSoldState;

import lombok.Getter;

@Getter
public final class AuctionRetrieveResponse {

	private final long auctionItemId;

	private final String managerId;

	private final String managerName;

	private final String managerCourt;

	private final String managerDepartment;

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

	private final boolean isBookmarked;

	private final List<ImageFileUrlResponse> imageFileUrlResponseList;

	private final List<CommentDetailsResponse> commentDetailsResponseList;

	public AuctionRetrieveResponse(long auctionItemId, String managerId, String managerName, String managerCourt,
		String managerDepartment, String addressCode, String auctionItemCaseNumber, String auctionItemName,
		String location,
		String lotNumber, String addressDetail, Long appraisedValue, LocalDateTime auctionStartDate,
		LocalDateTime auctionEndDate, ItemCategory itemCategory, Double areaSize, Integer auctionFailedCount,
		ItemSoldState itemSoldState, Integer bookmarkCount, Integer hit, boolean isBookmarked,
		List<ImageFileUrlResponse> imageFileUrlResponseList, List<CommentDetailsResponse> commentDetailsResponseList) {
		this.auctionItemId = auctionItemId;
		this.managerId = managerId;
		this.managerName = managerName;
		this.managerCourt = managerCourt;
		this.managerDepartment = managerDepartment;
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
		this.isBookmarked = isBookmarked;
		this.imageFileUrlResponseList = imageFileUrlResponseList;
		this.commentDetailsResponseList = commentDetailsResponseList;
	}
}
