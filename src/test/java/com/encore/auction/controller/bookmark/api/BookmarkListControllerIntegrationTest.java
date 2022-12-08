package com.encore.auction.controller.bookmark.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.controller.bookmark.responses.BookmarkDetailsListResponse;

@SpringBootTest
@Transactional
class BookmarkListControllerIntegrationTest {

	@Autowired
	private BookmarkListController bookmarkListController;

	private final String userId = "tester1";
	private final String bookmarkNotExistUserId = "jji0428";

	@Test
	@DisplayName("Retrieve Bookmark List By UserId Integration Test - Success")
	void retrieveBookmarkListSuccess() {
		//given //when
		ResponseEntity<BookmarkDetailsListResponse> bookmarkDetailsListResponse = bookmarkListController.retrieveBookmarkListByUserId(
			userId);
		//then
		assertTrue(0 < bookmarkDetailsListResponse.getBody().getBookmarkDetailsResponseList().size());
	}

	@Test
	@DisplayName("Retrieve Bookmark List By UserId Not Exist Bookmark List Integration Test - Success")
	void retrieveBookmarkListBookmarkNotExistSuccess() throws Exception {
		//given //when
		ResponseEntity<BookmarkDetailsListResponse> bookmarkDetailsListResponse = bookmarkListController.retrieveBookmarkListByUserId(
			bookmarkNotExistUserId);
		//then
		assertEquals(0, bookmarkDetailsListResponse.getBody().getBookmarkDetailsResponseList().size());
	}
}
