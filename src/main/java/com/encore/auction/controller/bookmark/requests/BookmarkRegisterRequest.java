package com.encore.auction.controller.bookmark.requests;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public final class BookmarkRegisterRequest {

	@NotNull
	private final Long auctionId;

	public BookmarkRegisterRequest(Long auctionId) {
		this.auctionId = auctionId;
	}
}
