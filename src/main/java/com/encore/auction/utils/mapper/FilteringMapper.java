package com.encore.auction.utils.mapper;

import com.encore.auction.controller.filtering.responses.FilteringItemsResponse;
import com.encore.auction.model.auction.item.AuctionItem;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilteringMapper {

	private static FilteringMapper filteringMapper = null;

	public static FilteringMapper of() {
		if (filteringMapper == null) {
			filteringMapper = new FilteringMapper();
		}
		return filteringMapper;
	}

	public FilteringItemsResponse entityToFilteringItemsResponse(AuctionItem auctionItems) {
		return new FilteringItemsResponse(auctionItems.getId(), auctionItems.getManager().getId(),
			auctionItems.getAddress().getAddressCode(), auctionItems.getAuctionItemCaseNumber(),
			auctionItems.getAuctionItemName(), auctionItems.getLocation(), auctionItems.getLotNumber(),
			auctionItems.getAddressDetail(), auctionItems.getAppraisedValue(), auctionItems.getAuctionStartDate(),
			auctionItems.getAuctionEndDate(), auctionItems.getItemCategory(), auctionItems.getAreaSize(),
			auctionItems.getAuctionFailedCount(), auctionItems.getItemSoldState(), auctionItems.getBookmarkCount(),
			auctionItems.getHit(),
			auctionItems.getState());
	}
}
