package com.encore.auction.utils.mapper;

import com.encore.auction.controller.bookmark.responses.BookmarkDeleteResponse;
import com.encore.auction.controller.bookmark.responses.BookmarkRegisterResponse;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.bookmark.Bookmark;
import com.encore.auction.model.bookmark.BookmarkId;
import com.encore.auction.model.user.User;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookmarkMapper {

	private static BookmarkMapper bookmarkMapper = null;

	public static BookmarkMapper of() {
		if (bookmarkMapper == null) {
			bookmarkMapper = new BookmarkMapper();
		}
		return bookmarkMapper;
	}

	public BookmarkId requestToBookmarkId(User user, AuctionItem auctionItem) {
		return new BookmarkId(user, auctionItem);
	}

	public Bookmark createBookmarkIfNotExistUpdateBookmarkIfExist(BookmarkId bookmarkId) {
		return Bookmark.builder().bookmarkId(bookmarkId).state(false).build();
	}

	public BookmarkRegisterResponse bookmarkRegisterResponse(Bookmark savedBookmark) {
		return new BookmarkRegisterResponse(savedBookmark.getBookmarkId().getUser().getId(),
			savedBookmark.getBookmarkId().getAuctionItem().getId(),
			savedBookmark.getState());
	}

	public BookmarkDeleteResponse bookmarkToDeleteResponse(Bookmark bookmark) {
		return new BookmarkDeleteResponse(bookmark.getBookmarkId().getUser().getId(),
			bookmark.getBookmarkId().getAuctionItem().getId(), bookmark.getState());
	}
}
