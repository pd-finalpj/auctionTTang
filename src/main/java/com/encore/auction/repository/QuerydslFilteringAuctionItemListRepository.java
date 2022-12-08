package com.encore.auction.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.encore.auction.controller.filtering.responses.FilteringItemsResponse;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.auction.item.ItemCategory;
import com.encore.auction.model.auction.item.QAuctionItem;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class QuerydslFilteringAuctionItemListRepository extends QuerydslRepositorySupport
	implements FilteringAuctionItemListRepository {

	private QAuctionItem qAuctionItem = QAuctionItem.auctionItem;

	public QuerydslFilteringAuctionItemListRepository() {
		super(AuctionItem.class);
	}

	public List<FilteringItemsResponse> filteringAuctionItemList(String address, String date, String category,
		Integer auctionFailedCount) {

		return from(qAuctionItem)
			.select(Projections.constructor(FilteringItemsResponse.class, qAuctionItem.id, qAuctionItem.manager,
				qAuctionItem.address,
				qAuctionItem.auctionItemName,
				qAuctionItem.location, qAuctionItem.lotNumber, qAuctionItem.addressDetail,
				qAuctionItem.appraisedValue, qAuctionItem.auctionStartDate,
				qAuctionItem.auctionEndDate, qAuctionItem.itemCategory, qAuctionItem.areaSize,
				qAuctionItem.auctionFailedCount,
				qAuctionItem.hit,
				qAuctionItem.state))
			.where(eqAddress(address), loeDate(date), eqCategory(category), loeAuctionFailedCount(auctionFailedCount))
			.orderBy(qAuctionItem.id.desc())
			.fetch();
	}

	public BooleanExpression eqAddress(String address) {
		return address != null ? qAuctionItem.address.cityName.contains(address) : null;
	}

	public BooleanExpression loeDate(String date) {
		return date != null ? qAuctionItem.auctionEndDate.goe(LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE)) :
			null;
	}

	public BooleanExpression eqCategory(String category) {
		return category != null ? qAuctionItem.itemCategory.eq(ItemCategory.valueOf(category)) : null;
	}

	public BooleanExpression loeAuctionFailedCount(Integer auctionFailedCount) {
		return auctionFailedCount != null ? qAuctionItem.auctionFailedCount.loe(auctionFailedCount) : null;
	}

}
