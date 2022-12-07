package com.encore.auction.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.encore.auction.controller.bidding.bidding.responses.BiddingRetrieveResponse;
import com.encore.auction.model.auction.item.QAuctionItem;
import com.encore.auction.model.bidding.aftbidding.QAftBidding;
import com.encore.auction.model.bidding.bidding.Bidding;
import com.encore.auction.model.bidding.bidding.QBidding;
import com.querydsl.core.types.Projections;

@Repository
public class QuerydslBiddingRetrieveRepository extends QuerydslRepositorySupport implements BiddingRetrieveRepository {

	QBidding qBidding = QBidding.bidding;
	QAuctionItem qAuctionItem = QAuctionItem.auctionItem;
	QAftBidding qAftBidding = QAftBidding.aftBidding;

	public QuerydslBiddingRetrieveRepository() {
		super(Bidding.class);
	}

	@Override
	public BiddingRetrieveResponse retrieveBiddingByBiddingId(Long biddingId) {
		return from(qBidding)
			.select(Projections.constructor(BiddingRetrieveResponse.class, qBidding.id, qAuctionItem.id,
				qAuctionItem.auctionItemName, qBidding.biddingDate, qBidding.amount, qAftBidding.decideDate,
				qAftBidding.biddingResult))
			.leftJoin(qAuctionItem)
			.fetchJoin()
			.on(qAuctionItem.id.eq(qBidding.auctionItem.id))
			.leftJoin(qAftBidding)
			.fetchJoin()
			.on(qAftBidding.bidding.id.eq(qBidding.id))
			.where(qBidding.id.eq(biddingId).and(qBidding.state.isFalse()))
			.fetchOne();
	}
}
