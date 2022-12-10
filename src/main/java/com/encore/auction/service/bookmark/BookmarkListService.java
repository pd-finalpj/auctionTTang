package com.encore.auction.service.bookmark;

import java.util.List;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.bookmark.responses.BookmarkDetailsListResponse;
import com.encore.auction.controller.bookmark.responses.BookmarkDetailsResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.exception.WrongRequestException;
import com.encore.auction.model.user.User;
import com.encore.auction.repository.BookmarkListRetrieveRepository;
import com.encore.auction.repository.UserRepository;
import com.encore.auction.utils.token.JwtProvider;

@Service
public class BookmarkListService {

	private final UserRepository userRepository;
	private final BookmarkListRetrieveRepository bookmarkListRetrieveRepository;
	private final JwtProvider jwtProvider;

	public BookmarkListService(UserRepository userRepository,
		BookmarkListRetrieveRepository bookmarkListRetrieveRepository,
		JwtProvider jwtProvider) {
		this.userRepository = userRepository;
		this.bookmarkListRetrieveRepository = bookmarkListRetrieveRepository;
		this.jwtProvider = jwtProvider;
	}

	public BookmarkDetailsListResponse retrieveBookmarkListByUserId(String token) {
		String userId = checkTokenIsUserAndGetUserID(token);

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User does not exist"));

		List<BookmarkDetailsResponse> bookmarkDetailsResponseList = bookmarkListRetrieveRepository.retrieveBookmarkListByUserId(
			user.getId());

		return new BookmarkDetailsListResponse(userId, bookmarkDetailsResponseList);
	}

	private String checkTokenIsUserAndGetUserID(String token) {
		if (jwtProvider.getAudience(token).equals("manager"))
			throw new WrongRequestException("Manager Token can't do user's thing");
		return jwtProvider.getSubject(token);
	}
}
