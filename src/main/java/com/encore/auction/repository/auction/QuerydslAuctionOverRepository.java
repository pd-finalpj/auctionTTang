package com.encore.auction.repository.auction;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.encore.auction.controller.auction.responses.AuctionOverDateResponse;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.auction.item.QAuctionItem;
import com.encore.auction.repository.auction.AuctionOverRepository;
import com.querydsl.core.types.Projections;

@Repository
public class QuerydslAuctionOverRepository extends QuerydslRepositorySupport implements AuctionOverRepository {

	private QAuctionItem qAuctionItem = QAuctionItem.auctionItem;

	public QuerydslAuctionOverRepository() {
		super(AuctionItem.class);
	}

	@Override
	public List<AuctionOverDateResponse> auctionOverListByStartDateAndEndDate(LocalDateTime now) {
		return from(qAuctionItem)
			.select(
				Projections.constructor(AuctionOverDateResponse.class, qAuctionItem.id, qAuctionItem.auctionStartDate,
					qAuctionItem.auctionEndDate))
			.where(qAuctionItem.auctionEndDate.goe(now).and(qAuctionItem.auctionEndDate.loe(now.plusHours(1))))
			.fetch();
	}
}
