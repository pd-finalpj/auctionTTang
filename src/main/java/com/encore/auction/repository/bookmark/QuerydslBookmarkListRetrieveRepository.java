package com.encore.auction.repository.bookmark;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.encore.auction.controller.bookmark.responses.BookmarkDetailsResponse;
import com.encore.auction.model.address.QAddress;
import com.encore.auction.model.auction.item.QAuctionItem;
import com.encore.auction.model.bookmark.Bookmark;
import com.encore.auction.model.bookmark.QBookmark;
import com.querydsl.core.types.Projections;

@Repository
public class QuerydslBookmarkListRetrieveRepository extends QuerydslRepositorySupport
	implements BookmarkListRetrieveRepository {

	private QBookmark qBookmark = QBookmark.bookmark;
	private QAuctionItem qAuctionItem = QAuctionItem.auctionItem;
	private QAddress qAddress = QAddress.address;

	public QuerydslBookmarkListRetrieveRepository() {
		super(Bookmark.class);
	}

	@Override
	public List<BookmarkDetailsResponse> retrieveBookmarkListByUserId(String userId) {
		return from(qBookmark)
			.select(Projections.constructor(BookmarkDetailsResponse.class, qAuctionItem.id, qAddress.addressCode,
				qAuctionItem.auctionItemName, qAuctionItem.location, qAuctionItem.lotNumber, qAuctionItem.addressDetail,
				qAuctionItem.appraisedValue, qAuctionItem.auctionStartDate, qAuctionItem.auctionEndDate,
				qAuctionItem.itemCategory, qAuctionItem.areaSize, qAuctionItem.auctionFailedCount, qAuctionItem.hit))
			.leftJoin(qAuctionItem)
			.fetchJoin()
			.on(qAuctionItem.id.eq(qBookmark.bookmarkId.auctionItem.id))
			.leftJoin(qAddress)
			.fetchJoin()
			.on(qAddress.addressCode.eq(qAuctionItem.address.addressCode))
			.where(qBookmark.bookmarkId.user.id.eq(userId).and(qBookmark.state.isFalse()))
			.fetch();
	}

	@Override
	public Long countBookmark(String userId) {
		return from(qBookmark)
			.select(qBookmark.count())
			.where(qBookmark.bookmarkId.user.id.eq(userId))
			.fetchOne();
	}
}
