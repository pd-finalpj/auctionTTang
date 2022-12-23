package com.encore.auction.controller.bookmark.responses;

import lombok.Getter;

@Getter
public final class BookmarkCountResponse {

	private final Integer bookmarkCount;

	public BookmarkCountResponse(Integer bookmarkCount) {
		this.bookmarkCount = bookmarkCount;
	}
}
