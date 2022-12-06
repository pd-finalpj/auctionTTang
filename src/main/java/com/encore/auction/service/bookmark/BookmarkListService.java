package com.encore.auction.service.bookmark;

import java.util.List;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.bookmark.responses.BookmarkDetailsListResponse;
import com.encore.auction.controller.bookmark.responses.BookmarkDetailsResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.model.user.User;
import com.encore.auction.repository.BookmarkListRetrieveRepository;
import com.encore.auction.repository.UserRepository;

@Service
public class BookmarkListService {

	private final UserRepository userRepository;
	private final BookmarkListRetrieveRepository bookmarkListRetrieveRepository;

	public BookmarkListService(UserRepository userRepository,
		BookmarkListRetrieveRepository bookmarkListRetrieveRepository) {
		this.userRepository = userRepository;
		this.bookmarkListRetrieveRepository = bookmarkListRetrieveRepository;
	}

	public BookmarkDetailsListResponse retrieveBookmarkListByUserId(String userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User does not exist"));

		List<BookmarkDetailsResponse> bookmarkDetailsResponseList = bookmarkListRetrieveRepository.retrieveBookmarkListByUserId(
			user.getId());
		
		return new BookmarkDetailsListResponse(userId, bookmarkDetailsResponseList);
	}
}
