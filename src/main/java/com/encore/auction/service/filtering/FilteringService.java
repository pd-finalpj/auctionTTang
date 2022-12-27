package com.encore.auction.service.filtering;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.encore.auction.controller.filtering.requests.FilteringAuctionItemRequest;
import com.encore.auction.controller.filtering.responses.FilteringItemsListByManagerIdResponse;
import com.encore.auction.controller.filtering.responses.FilteringItemsListResponse;
import com.encore.auction.controller.filtering.responses.FilteringItemsResponse;
import com.encore.auction.model.filtering.Filtering;
import com.encore.auction.repository.filtering.FilteringAuctionItemListRepository;
import com.encore.auction.repository.filtering.FilteringRedisRepository;
import com.encore.auction.utils.mapper.FilteringMapper;
import com.encore.auction.utils.token.JwtProvider;

@Service
public class FilteringService {

	private final FilteringAuctionItemListRepository filteringAuctionItemListRepository;
	private final FilteringRedisRepository filteringRedisRepository;
	private final JwtProvider jwtProvider;

	public FilteringService(FilteringAuctionItemListRepository filteringAuctionItemListRepository,
		FilteringRedisRepository filteringRedisRepository, JwtProvider jwtProvider) {
		this.filteringAuctionItemListRepository = filteringAuctionItemListRepository;
		this.filteringRedisRepository = filteringRedisRepository;
		this.jwtProvider = jwtProvider;
	}

	public FilteringItemsListResponse getFilteredAuctionItemList(
		FilteringAuctionItemRequest filteringAuctionItemRequest) {
		String redisId = getRedisId(filteringAuctionItemRequest);

		Optional<Filtering> filtering = filteringRedisRepository.findById(redisId);

		if (filtering.isPresent()) {
			try {
				return FilteringMapper.of().redisFilteringToResponse(filtering.get());
			} catch (Exception e) {
				return getFilteringItemsListResponse(filteringAuctionItemRequest, redisId);
			}
		} else {
			return getFilteringItemsListResponse(filteringAuctionItemRequest, redisId);
		}
	}

	private FilteringItemsListResponse getFilteringItemsListResponse(
		FilteringAuctionItemRequest filteringAuctionItemRequest, String redisId) {
		PageRequest pageRequest = convertPageRequest(filteringAuctionItemRequest);

		Slice<FilteringItemsResponse> filteringItemsResponses = filteringAuctionItemListRepository.filteringAuctionItemList(
			filteringAuctionItemRequest, pageRequest);

		Filtering filteringCache = FilteringMapper.of().filteringToRedisFiltering(redisId, filteringItemsResponses);
		try {
			filteringRedisRepository.save(filteringCache);
		} catch (Exception ignored) {
		}

		return FilteringMapper.of()
			.filteringToResponse(filteringItemsResponses);
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

	public FilteringItemsListByManagerIdResponse findAuctionListByManagerId(String token) {
		String managerId = jwtProvider.checkTokenIsManagerAndGetManagerID(token);

		List<FilteringItemsResponse> filteringItemsResponseList = filteringAuctionItemListRepository.filteringAuctionItemListByManagerId(
			managerId);

		return new FilteringItemsListByManagerIdResponse(filteringItemsResponseList);
	}
}
