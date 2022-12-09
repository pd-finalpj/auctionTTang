package com.encore.auction.service.filtering;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.filtering.responses.FilteringItemsListResponse;
import com.encore.auction.controller.filtering.responses.FilteringItemsResponse;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.repository.FilteringAuctionItemListRepository;
import com.encore.auction.repository.FilteringRepository;
import com.encore.auction.utils.mapper.FilteringMapper;

@Service
public class FilteringService {

	private final FilteringRepository filteringRepository;

	private final FilteringAuctionItemListRepository filteringAuctionItemListRepository;

	public FilteringService(FilteringRepository filteringRepository,
		FilteringAuctionItemListRepository filteringAuctionItemListRepository) {
		this.filteringRepository = filteringRepository;
		this.filteringAuctionItemListRepository = filteringAuctionItemListRepository;
	}

	public FilteringItemsListResponse getAuctionItemList() {
		List<AuctionItem> auctionItems = filteringRepository.findAll();
		List<FilteringItemsResponse> auctionItemList = new ArrayList<FilteringItemsResponse>();
		for (AuctionItem auctionItem : auctionItems) {
			auctionItemList.add(FilteringMapper.of().entityToFilteringItemsResponse(auctionItem));
		}
		return new FilteringItemsListResponse(auctionItemList);
	}

	public FilteringItemsListResponse getFilteredAuctionItemList(String address, String date, String category,
		Integer auctionFailedCount) {
		return new FilteringItemsListResponse(
			filteringAuctionItemListRepository.filteringAuctionItemList(address, date, category, auctionFailedCount));
	}

}
