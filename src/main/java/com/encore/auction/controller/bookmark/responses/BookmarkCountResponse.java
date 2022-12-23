package com.encore.auction.controller.bookmark.responses;

import lombok.Getter;

@Getter
public final class BookmarkCountResponse {

	private final Long bookmarkCount;

	public BookmarkCountResponse(Long bookmarkCount) {
		this.bookmarkCount = bookmarkCount;
	}
}
