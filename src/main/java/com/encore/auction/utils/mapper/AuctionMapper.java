package com.encore.auction.utils.mapper;

import java.util.List;

import com.encore.auction.controller.auction.requests.AuctionCreateRequest;
import com.encore.auction.controller.auction.responses.AuctionDeleteResponse;
import com.encore.auction.controller.auction.responses.AuctionDetailsResponse;
import com.encore.auction.controller.auction.responses.AuctionIdResponse;
import com.encore.auction.controller.auction.responses.AuctionRetrieveResponse;
import com.encore.auction.controller.comment.responses.CommentDetailsResponse;
import com.encore.auction.controller.imagefile.responses.ImageFileUrlResponse;
import com.encore.auction.model.address.Address;
import com.encore.auction.model.auction.failed.log.AuctionFailedLog;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.auction.item.ItemSoldState;
import com.encore.auction.model.manager.Manager;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuctionMapper {
	private static AuctionMapper auctionMapper = null;

	public static AuctionMapper of() {
		if (auctionMapper == null) {
			auctionMapper = new AuctionMapper();
		}
		return auctionMapper;
	}

	public AuctionIdResponse entityToAuctionItemIdResponse(AuctionItem auctionItemId) {
		return new AuctionIdResponse(auctionItemId.getId());
	}

	public AuctionItem createRequestToEntity(AuctionCreateRequest auctionCreateRequest, Manager manager,
		Address address) {
		return AuctionItem.builder()
			.manager(manager)
			.address(address)
			.auctionItemCaseNumber(auctionCreateRequest.getAuctionItemCaseNumber())
			.auctionItemName(auctionCreateRequest.getAuctionItemName())
			.location(auctionCreateRequest.getLocation())
			.lotNumber(auctionCreateRequest.getLotNumber())
			.addressDetail(auctionCreateRequest.getAddressDetail())
			.appraisedValue(auctionCreateRequest.getAppraisedValue())
			.auctionStartDate(auctionCreateRequest.getAuctionStartDate())
			.auctionEndDate(auctionCreateRequest.getAuctionEndDate())
			.location(auctionCreateRequest.getLocation())
			.itemCategory(auctionCreateRequest.getItemCategory())
			.areaSize(auctionCreateRequest.getAreaSize())
			.state(false)
			.hit(0)
			.bookmarkCount(0)
			.itemSoldState(ItemSoldState.PROCEEDING)
			.build();
	}

	public AuctionDetailsResponse entityToAuctionDetailsResponse(AuctionItem auctionItem) {
		return new AuctionDetailsResponse(auctionItem.getId(), auctionItem.getManager().getId(),
			auctionItem.getAddress().getAddressCode(), auctionItem.getAuctionItemCaseNumber(),
			auctionItem.getAuctionItemName(),
			auctionItem.getLocation(), auctionItem.getLotNumber(), auctionItem.getAddressDetail(),
			auctionItem.getAppraisedValue(), auctionItem.getAuctionStartDate(), auctionItem.getAuctionEndDate(),
			auctionItem.getItemCategory(),
			auctionItem.getAreaSize(), auctionItem.getAuctionFailedCount(), auctionItem.getItemSoldState(),
			auctionItem.getBookmarkCount(),
			auctionItem.getHit());
	}

	public AuctionDeleteResponse entityToAuctionDeleteResponse(AuctionItem auctionItem) {
		return new AuctionDeleteResponse(auctionItem.getId(), auctionItem.getState());
	}

	public AuctionRetrieveResponse entityToAuctionRetrieveResponse(AuctionItem auctionItem, boolean isBookmarked,
		Manager manager,
		List<CommentDetailsResponse> commentDetailsResponseList, List<ImageFileUrlResponse> imageFileUrlResponses) {
		return new AuctionRetrieveResponse(auctionItem.getId(), manager.getId(), manager.getName(), manager.getCourt(),
			manager.getDepartment(),
			auctionItem.getAddress().getAddressCode(), auctionItem.getAuctionItemCaseNumber(),
			auctionItem.getAuctionItemName(),
			auctionItem.getLocation(), auctionItem.getLotNumber(), auctionItem.getAddressDetail(),
			auctionItem.getAppraisedValue(), auctionItem.getAuctionStartDate(), auctionItem.getAuctionEndDate(),
			auctionItem.getItemCategory(),
			auctionItem.getAreaSize(), auctionItem.getAuctionFailedCount(), auctionItem.getItemSoldState(),
			auctionItem.getBookmarkCount(),
			auctionItem.getHit(), isBookmarked, imageFileUrlResponses,
			commentDetailsResponseList);
	}

	public AuctionFailedLog entityToAuctionFailedLog(AuctionItem auctionItem) {
		return AuctionFailedLog.builder()
			.auctionItem(auctionItem)
			.appraisedValue(auctionItem.getAppraisedValue())
			.auctionStartDate(auctionItem.getAuctionStartDate())
			.auctionEndDate(auctionItem.getAuctionEndDate())
			.build();
	}
}
