package com.encore.auction.repository.filtering;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.encore.auction.controller.filtering.requests.FilteringAuctionItemRequest;
import com.encore.auction.controller.filtering.requests.SortCategory;
import com.encore.auction.controller.filtering.responses.FilteringItemsResponse;
import com.encore.auction.model.address.QAddress;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.auction.item.ItemCategory;
import com.encore.auction.model.auction.item.QAuctionItem;
import com.encore.auction.utils.validator.LocalDateTimeValidator;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class QuerydslFilteringAuctionItemListRepository extends QuerydslRepositorySupport
	implements FilteringAuctionItemListRepository {

	private QAuctionItem qAuctionItem = QAuctionItem.auctionItem;
	private QAddress qAddress = QAddress.address;

	public QuerydslFilteringAuctionItemListRepository() {
		super(AuctionItem.class);
	}

	public Slice<FilteringItemsResponse> filteringAuctionItemList(
		FilteringAuctionItemRequest filteringAuctionItemRequest, Pageable pageable) {
		List<FilteringItemsResponse> filteringItemsResponseList = from(qAuctionItem)
			.select(Projections.constructor(FilteringItemsResponse.class, qAuctionItem.id, qAuctionItem.manager.id,
				qAddress.addressCode, qAuctionItem.auctionItemCaseNumber,
				qAuctionItem.auctionItemName, qAddress.stateName, qAddress.cityName,
				qAuctionItem.location, qAuctionItem.lotNumber, qAuctionItem.addressDetail,
				qAuctionItem.appraisedValue, qAuctionItem.auctionStartDate,
				qAuctionItem.auctionEndDate, qAuctionItem.itemCategory, qAuctionItem.areaSize,
				qAuctionItem.auctionFailedCount, qAuctionItem.itemSoldState))
			.leftJoin(qAddress).on(qAuctionItem.address.addressCode.eq(qAddress.addressCode))
			.where(eqAddressCode(filteringAuctionItemRequest.getAddressCode()),
				loeDate(filteringAuctionItemRequest.getDate()), eqCategory(
					filteringAuctionItemRequest.getCategory()),
				loeAuctionFailedCount(filteringAuctionItemRequest.getAuctionFailedCount()),
				qAuctionItem.state.isFalse())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1)
			.orderBy(sortBy(filteringAuctionItemRequest.getSortCategory()))
			.fetch();

		boolean hasNext = false;
		if (filteringItemsResponseList.size() > pageable.getPageSize()) {
			filteringItemsResponseList.remove(pageable.getPageSize());
			hasNext = true;
		}
		return new SliceImpl<>(filteringItemsResponseList, pageable, hasNext);
	}

	public BooleanExpression eqAddressCode(String addressCode) {
		return addressCode != null ? qAddress.addressCode.eq(addressCode) : null;
	}

	public BooleanExpression loeDate(String date) {
		return date != null ?
			qAuctionItem.auctionEndDate.between(
				LocalDateTimeValidator.of().convertStringToLocalDateTime(date).minusHours(3),
				LocalDateTimeValidator.of().convertStringToLocalDateTime(date)) :
			null;
	}

	public BooleanExpression eqCategory(String category) {
		return category != null ? qAuctionItem.itemCategory.eq(ItemCategory.valueOf(category)) : null;
	}

	public BooleanExpression loeAuctionFailedCount(Integer auctionFailedCount) {
		return auctionFailedCount != null ? qAuctionItem.auctionFailedCount.loe(auctionFailedCount) : null;
	}

	public OrderSpecifier<?> sortBy(SortCategory sortCategory) {
		if (sortCategory == null) {
			return qAuctionItem.hit.desc();
		} else if (sortCategory.equals(SortCategory.BOOKMARK)) {
			return qAuctionItem.bookmarkCount.desc();
		} else if (sortCategory.equals(SortCategory.NEWEST)) {
			return qAuctionItem.id.desc();
		} else if (sortCategory.equals(SortCategory.AMOUNT)) {
			return qAuctionItem.appraisedValue.asc();
		} else {
			return qAuctionItem.hit.desc();
		}
	}
}
