package com.encore.auction.controller.bookmark.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.bookmark.responses.BookmarkDetailsListResponse;
import com.encore.auction.service.bookmark.BookmarkListService;
import com.encore.auction.utils.security.Permission;

@RestController
@RequestMapping("/bookmark-list")
public class BookmarkListController {

	private final BookmarkListService bookmarkListService;

	public BookmarkListController(BookmarkListService bookmarkListService) {
		this.bookmarkListService = bookmarkListService;
	}

	@Permission
	@GetMapping
	public ResponseEntity<BookmarkDetailsListResponse> retrieveBookmarkListByUserId(
		@RequestHeader("Token") String token) {
		return ResponseEntity.ok().body(bookmarkListService.retrieveBookmarkListByUserId(token));
	}
}
