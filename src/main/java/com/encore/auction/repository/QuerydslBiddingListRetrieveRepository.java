package com.encore.auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsResponse;
import com.encore.auction.model.auction.item.QAuctionItem;
import com.encore.auction.model.bidding.aftbidding.QAftBidding;
import com.encore.auction.model.bidding.bidding.Bidding;
import com.encore.auction.model.bidding.bidding.QBidding;
import com.querydsl.core.types.Projections;

@Repository
public class QuerydslBiddingListRetrieveRepository extends QuerydslRepositorySupport
	implements BiddingListRetrieveRepository {

	private QBidding qBidding = QBidding.bidding;
	private QAuctionItem qAuctionItem = QAuctionItem.auctionItem;
	private QAftBidding qAftBidding = QAftBidding.aftBidding;

	public QuerydslBiddingListRetrieveRepository() {
		super(Bidding.class);
	}

	@Override
	public List<BiddingDetailsResponse> retrieveBiddingListByUserId(String userId) {
		return from(qBidding)
			.select(Projections.constructor(BiddingDetailsResponse.class, qBidding.id, qAuctionItem.id,
				qAuctionItem.auctionItemName, qBidding.biddingDate, qBidding.amount))
			.leftJoin(qAuctionItem)
			.fetchJoin()
			.on(qAuctionItem.id.eq(qBidding.auctionItem.id))
			.leftJoin(qAftBidding)
			.fetchJoin()
			.on(qAftBidding.bidding.id.eq(qBidding.id))
			.where(qBidding.user.id.eq(userId).and(qAftBidding.id.isNull()))
			.fetch();
	}
}
