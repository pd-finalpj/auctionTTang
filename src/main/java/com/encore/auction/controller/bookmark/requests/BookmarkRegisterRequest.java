package com.encore.auction.controller.bookmark.requests;

import lombok.Getter;

@Getter
public final class BookmarkRegisterRequest {

	private final String userId;
	private final Long auctionId;

	public BookmarkRegisterRequest(String userId, Long auctionId) {
		this.userId = userId;
		this.auctionId = auctionId;
	}
}
