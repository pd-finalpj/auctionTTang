package com.encore.auction.model.bookmark;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.user.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookmarkId implements Serializable {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "auction_item_id")
	private AuctionItem auctionItem;

	public BookmarkId(User user, AuctionItem auctionItem) {
		this.user = user;
		this.auctionItem = auctionItem;
	}
}
