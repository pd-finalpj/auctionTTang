package com.encore.auction.service.bookmark;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.bookmark.requests.BookmarkRegisterRequest;
import com.encore.auction.controller.bookmark.responses.BookmarkDeleteResponse;
import com.encore.auction.controller.bookmark.responses.BookmarkRegisterResponse;

@Service
public class BookmarkService {
	public BookmarkRegisterResponse registerBookmark(BookmarkRegisterRequest bookmarkRegisterRequest) {
		return null;
	}

	public BookmarkDeleteResponse deleteBookmark(Long auctionId, String userId) {
		return null;
	}
}
