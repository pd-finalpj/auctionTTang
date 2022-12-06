package com.encore.auction.controller.bookmark.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.bookmark.responses.BookmarkDetailsListResponse;
import com.encore.auction.service.bookmark.BookmarkListService;

@RestController
@RequestMapping("/bookmark-list")
public class BookmarkListController {

	private final BookmarkListService bookmarkListService;

	public BookmarkListController(BookmarkListService bookmarkListService) {
		this.bookmarkListService = bookmarkListService;
	}

	@GetMapping("/{user-id}")
	public ResponseEntity<BookmarkDetailsListResponse> retrieveBookmarkListByUserId(
		@PathVariable("user-id") String userId) {
		return ResponseEntity.ok().body(bookmarkListService.retrieveBookmarkListByUserId(userId));
	}
}
