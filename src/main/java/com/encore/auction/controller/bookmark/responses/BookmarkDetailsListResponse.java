package com.encore.auction.controller.bookmark.responses;

import java.util.List;

import lombok.Getter;

@Getter
public final class BookmarkDetailsListResponse {

	private final String userId;
	private final List<BookmarkDetailsResponse> bookmarkDetailsResponseList;

	public BookmarkDetailsListResponse(String userId, List<BookmarkDetailsResponse> bookmarkDetailsResponseList) {
		this.userId = userId;
		this.bookmarkDetailsResponseList = bookmarkDetailsResponseList;
	}
}
