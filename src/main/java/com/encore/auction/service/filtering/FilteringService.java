package com.encore.auction.service.filtering;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.encore.auction.controller.filtering.requests.FilteringAuctionItemRequest;
import com.encore.auction.controller.filtering.responses.FilteringItemsListResponse;
import com.encore.auction.controller.filtering.responses.FilteringItemsResponse;
import com.encore.auction.model.filtering.Filtering;
import com.encore.auction.repository.filtering.FilteringAuctionItemListRepository;
import com.encore.auction.repository.filtering.FilteringRedisRepository;
import com.encore.auction.repository.filtering.FilteringRepository;
import com.encore.auction.utils.mapper.FilteringMapper;

@Service
public class FilteringService {

	private final FilteringRepository filteringRepository;
	private final FilteringAuctionItemListRepository filteringAuctionItemListRepository;
	private final FilteringRedisRepository filteringRedisRepository;

	public FilteringService(FilteringRepository filteringRepository,
		FilteringAuctionItemListRepository filteringAuctionItemListRepository,
		FilteringRedisRepository filteringRedisRepository) {
		this.filteringRepository = filteringRepository;
		this.filteringAuctionItemListRepository = filteringAuctionItemListRepository;
		this.filteringRedisRepository = filteringRedisRepository;
	}

	public FilteringItemsListResponse getFilteredAuctionItemList(
		FilteringAuctionItemRequest filteringAuctionItemRequest) {
		String redisId = getRedisId(filteringAuctionItemRequest);

		Optional<Filtering> filtering = filteringRedisRepository.findById(redisId);

		if (filtering.isPresent()) {
			return FilteringMapper.of().redisFilteringToResponse(filtering.get());
		} else {
			PageRequest pageRequest = convertPageRequest(filteringAuctionItemRequest);

			Slice<FilteringItemsResponse> filteringItemsResponses = filteringAuctionItemListRepository.filteringAuctionItemList(
				filteringAuctionItemRequest, pageRequest);

			Filtering filteringCache = FilteringMapper.of().filteringToRedisFiltering(redisId, filteringItemsResponses);

			filteringRedisRepository.save(filteringCache);

			return FilteringMapper.of()
				.filteringToResponse(filteringItemsResponses);
		}
	}

	private static PageRequest convertPageRequest(FilteringAuctionItemRequest filteringAuctionItemRequest) {
		int pageNum = filteringAuctionItemRequest.getPageNum() != null ? filteringAuctionItemRequest.getPageNum() : 1;
		int amount = filteringAuctionItemRequest.getAmount() != null ? filteringAuctionItemRequest.getAmount() : 10;

		return PageRequest.of(pageNum - 1, amount);
	}

	private String getRedisId(FilteringAuctionItemRequest filteringAuctionItemRequest) {
		String redisId = "filtering";

		if (filteringAuctionItemRequest.getAddressCode() != null)
			redisId = redisId + filteringAuctionItemRequest.getAddressCode();

		if (filteringAuctionItemRequest.getDate() != null)
			redisId = redisId + filteringAuctionItemRequest.getDate();

		if (filteringAuctionItemRequest.getCategory() != null)
			redisId = redisId + filteringAuctionItemRequest.getCategory();

		if (filteringAuctionItemRequest.getAuctionFailedCount() != null)
			redisId = redisId + filteringAuctionItemRequest.getAuctionFailedCount();

		if (filteringAuctionItemRequest.getSortCategory() != null)
			redisId = redisId + filteringAuctionItemRequest.getSortCategory();

		if (filteringAuctionItemRequest.getAmount() != null)
			redisId = redisId + filteringAuctionItemRequest.getAmount();

		if (filteringAuctionItemRequest.getPageNum() != null)
			redisId = redisId + filteringAuctionItemRequest.getPageNum();

		return redisId;
	}
}
