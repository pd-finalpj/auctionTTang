package com.encore.auction.controller.bookmark.responses;

import lombok.Getter;

@Getter
public final class BookmarkRegisterResponse {

	private final String userId;
	private final Long auctionId;
	private final boolean state;

	public BookmarkRegisterResponse(String userId, Long auctionId, boolean state) {
		this.userId = userId;
		this.auctionId = auctionId;
		this.state = state;
	}
}
