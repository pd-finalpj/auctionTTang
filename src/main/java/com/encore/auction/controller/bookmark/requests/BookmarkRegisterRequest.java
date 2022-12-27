package com.encore.auction.controller.bookmark.requests;

import lombok.Getter;

@Getter
public class BookmarkRegisterRequest {

	private Long auctionId;

	public BookmarkRegisterRequest(Long auctionId) {
		this.auctionId = auctionId;
	}

	public BookmarkRegisterRequest() {
	}
}
