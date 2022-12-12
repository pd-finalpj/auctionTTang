// package com.encore.auction.controller.bookmark.api;
//
// import static org.junit.jupiter.api.Assertions.*;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.ResponseEntity;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.encore.auction.controller.bookmark.requests.BookmarkRegisterRequest;
// import com.encore.auction.controller.bookmark.responses.BookmarkDeleteResponse;
// import com.encore.auction.controller.bookmark.responses.BookmarkRegisterResponse;
//
// @SpringBootTest
// @Transactional
// class BookmarkControllerIntegrationTest {
//
// 	@Autowired
// 	private BookmarkController bookmarkController;
//
// 	private final String userId = "tester1";
// 	private final Long auctionItemId = 4L;
// 	private final Long deleteWishBookmarkAuctionItemId = 1L;
// 	private final Long deletedBookmarkAuctionItemId = 2L;
//
// 	@Test
// 	@DisplayName("Bookmark Register Integration Test - Success")
// 	void bookmarkRegisterTestSuccess() {
// 		//given
// 		BookmarkRegisterRequest bookmarkRegisterRequest = new BookmarkRegisterRequest(userId, auctionItemId);
// 		//when
// 		ResponseEntity<BookmarkRegisterResponse> bookmarkRegisterResponse = bookmarkController.registerBookmark(
// 			bookmarkRegisterRequest);
// 		//then
// 		assertNotNull(bookmarkRegisterResponse.getBody().getAuctionId());
// 	}
//
// 	@Test
// 	@DisplayName("Deleted Bookmark Register Integration Test - Success")
// 	void deletedBookmarkRegisterTestSuccess() {
// 		//given
// 		BookmarkRegisterRequest bookmarkRegisterRequest = new BookmarkRegisterRequest(userId,
// 			deletedBookmarkAuctionItemId);
// 		//when
// 		ResponseEntity<BookmarkRegisterResponse> bookmarkRegisterResponse = bookmarkController.registerBookmark(
// 			bookmarkRegisterRequest);
// 		//then
// 		assertNotNull(bookmarkRegisterResponse.getBody().getAuctionId());
// 	}
//
// 	@Test
// 	@DisplayName("Deleted Bookmark Integration Test - Success")
// 	void deletedBookmarkTestSuccess() {
// 		//given //when
// 		ResponseEntity<BookmarkDeleteResponse> bookmarkDeleteResponse = bookmarkController.deleteBookmark(
// 			deleteWishBookmarkAuctionItemId, userId);
// 		//then
// 		assertNotNull(bookmarkDeleteResponse.getBody().getAuctionId());
// 	}
// }
