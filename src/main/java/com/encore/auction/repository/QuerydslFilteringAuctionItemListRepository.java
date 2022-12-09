package com.encore.auction.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.encore.auction.controller.filtering.responses.FilteringItemsResponse;
import com.encore.auction.model.address.QAddress;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.auction.item.ItemCategory;
import com.encore.auction.model.auction.item.QAuctionItem;
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

	public List<FilteringItemsResponse> filteringAuctionItemList(String stateName, String date, String category,
		Integer auctionFailedCount) {

		return from(qAuctionItem)
			.select(Projections.constructor(FilteringItemsResponse.class, qAuctionItem.id, qAuctionItem.manager.id,
				qAuctionItem.address.stateName,
				qAuctionItem.auctionItemName,
				qAuctionItem.location, qAuctionItem.lotNumber, qAuctionItem.addressDetail,
				qAuctionItem.appraisedValue, qAuctionItem.auctionStartDate,
				qAuctionItem.auctionEndDate, qAuctionItem.itemCategory, qAuctionItem.areaSize,
				qAuctionItem.auctionFailedCount,
				qAuctionItem.hit,
				qAuctionItem.state))
			.leftJoin(qAddress).on(qAuctionItem.address.addressCode.eq(qAddress.addressCode))
			.where(eqStateName(stateName), loeDate(date), eqCategory(category),
				loeAuctionFailedCount(auctionFailedCount))
			.orderBy(qAuctionItem.id.desc())
			.fetch();
	}

	public BooleanExpression eqStateName(String stateName) {
		return stateName != null ? qAuctionItem.address.stateName.eq(stateName) : null;
	}

	public BooleanExpression loeDate(String date) {
		return date != null ?
			qAuctionItem.auctionEndDate.between(LocalDateTime.parse(date,
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).minusHours(3),
				LocalDateTime.parse(date,
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))) :
			null;
	}

	public BooleanExpression eqCategory(String category) {
		return category != null ? qAuctionItem.itemCategory.eq(ItemCategory.valueOf(category)) : null;
	}

	public BooleanExpression loeAuctionFailedCount(Integer auctionFailedCount) {
		return auctionFailedCount != null ? qAuctionItem.auctionFailedCount.loe(auctionFailedCount) : null;
	}

}
