package com.encore.auction.utils.mapper;

import com.encore.auction.controller.auction.requests.AuctionCreateRequest;
import com.encore.auction.controller.auction.responses.AuctionIdResponse;
import com.encore.auction.model.address.Address;
import com.encore.auction.model.auction.item.AuctionItem;
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

	public AuctionIdResponse entityAuctionItemResponse(AuctionItem auctionItemId) {
		return new AuctionIdResponse(auctionItemId.getId());
	}

	public AuctionItem createRequestEntity(AuctionCreateRequest auctionCreateRequest, Manager manager,
		Address address) {
		return AuctionItem.builder()
			.manager(manager)
			.address(address)
			.auctionItemName(auctionCreateRequest.getAuctionItemName())
			.location(auctionCreateRequest.getLocation())
			.addressDetail(auctionCreateRequest.getAddressDetail())
			.appraisedValue(auctionCreateRequest.getAppraisedValue())
			.auctionStartDate(auctionCreateRequest.getAuctionStartDate())
			.auctionEndDate(auctionCreateRequest.getAuctionEndDate())
			.location(auctionCreateRequest.getLocation())
			.itemCategory(auctionCreateRequest.getItemCategory())
			.areaSize(auctionCreateRequest.getAreaSize())
			.state(false)
			.build();
	}
}
