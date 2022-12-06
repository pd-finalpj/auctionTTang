package com.encore.auction.repository;

import java.util.List;

import com.encore.auction.controller.bookmark.responses.BookmarkDetailsResponse;

public interface BookmarkListRetrieveRepository {

	public List<BookmarkDetailsResponse> retrieveBookmarkListByUserId(String userId);
}
