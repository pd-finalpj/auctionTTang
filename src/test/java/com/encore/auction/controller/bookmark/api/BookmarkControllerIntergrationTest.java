package com.encore.auction.controller.bookmark.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class BookmarkControllerIntergrationTest {

	@Autowired
	private BookmarkController bookmarkController;

	@Test
	@DisplayName("테스트")
	void bookmarkRegisterTestSuccess() {

	}
}
