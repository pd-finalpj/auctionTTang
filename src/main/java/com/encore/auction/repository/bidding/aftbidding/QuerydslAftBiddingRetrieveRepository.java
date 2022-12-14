package com.encore.auction.repository.bidding.aftbidding;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.encore.auction.controller.bidding.aftbidding.responses.AftBiddingDetailsResponse;
import com.encore.auction.model.auction.item.QAuctionItem;
import com.encore.auction.model.bidding.aftbidding.AftBidding;
import com.encore.auction.model.bidding.aftbidding.QAftBidding;
import com.encore.auction.model.bidding.bidding.QBidding;
import com.encore.auction.repository.bidding.aftbidding.AftBiddingRetrieveRepository;
import com.querydsl.core.types.Projections;

@Repository
public class QuerydslAftBiddingRetrieveRepository extends QuerydslRepositorySupport
	implements AftBiddingRetrieveRepository {

	private QBidding qBidding = QBidding.bidding;
	private QAftBidding qAftBidding = QAftBidding.aftBidding;
	private QAuctionItem qAuctionItem = QAuctionItem.auctionItem;

	public QuerydslAftBiddingRetrieveRepository() {
		super(AftBidding.class);
	}

	@Override
	public List<AftBiddingDetailsResponse> retrieveAftBiddingListByUserId(String userId) {
		return from(qAftBidding)
			.select(
				Projections.constructor(AftBiddingDetailsResponse.class, qAftBidding.id, qBidding.id, qAuctionItem.id,
					qAuctionItem.auctionItemName, qBidding.biddingDate, qBidding.amount, qAftBidding.decideDate,
					qAftBidding.biddingResult))
			.leftJoin(qBidding)
			.fetchJoin()
			.on(qBidding.id.eq(qAftBidding.bidding.id))
			.leftJoin(qAuctionItem)
			.fetchJoin()
			.on(qAuctionItem.id.eq(qBidding.auctionItem.id))
			.where(qBidding.user.id.eq(userId))
			.fetch();
	}
}
