package com.encore.auction.service.bookmark;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.controller.bookmark.requests.BookmarkRegisterRequest;
import com.encore.auction.controller.bookmark.responses.BookmarkDeleteResponse;
import com.encore.auction.controller.bookmark.responses.BookmarkRegisterResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.bookmark.Bookmark;
import com.encore.auction.model.bookmark.BookmarkId;
import com.encore.auction.model.user.User;
import com.encore.auction.repository.AuctionItemRepository;
import com.encore.auction.repository.BookmarkRepository;
import com.encore.auction.repository.UserRepository;
import com.encore.auction.utils.mapper.BookmarkMapper;

@Service
public class BookmarkService {

	private final BookmarkRepository bookmarkRepository;
	private final UserRepository userRepository;
	private final AuctionItemRepository auctionItemRepository;

	public BookmarkService(BookmarkRepository bookmarkRepository, UserRepository userRepository,
		AuctionItemRepository auctionItemRepository) {
		this.bookmarkRepository = bookmarkRepository;
		this.userRepository = userRepository;
		this.auctionItemRepository = auctionItemRepository;
	}

	@Transactional
	public BookmarkRegisterResponse registerBookmark(BookmarkRegisterRequest bookmarkRegisterRequest) {
		BookmarkId bookmarkId = checkUserAndAuctionAndGetBookmarkId(bookmarkRegisterRequest.getUserId(),
			bookmarkRegisterRequest.getAuctionId());

		Bookmark bookmark = BookmarkMapper.of().createBookmarkIfNotExistUpdateBookmarkIfExist(bookmarkId);

		Bookmark savedBookmark = bookmarkRepository.save(bookmark);

		return BookmarkMapper.of().bookmarkRegisterResponse(savedBookmark);
	}

	@Transactional
	public BookmarkDeleteResponse deleteBookmark(Long auctionId, String userId) {
		BookmarkId bookmarkId = checkUserAndAuctionAndGetBookmarkId(userId, auctionId);

		Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
			.orElseThrow(() -> new NonExistResourceException("Bookmark could not be found"));

		bookmark.deleteBookmark();

		return BookmarkMapper.of().bookmarkToDeleteResponse(bookmark);
	}

	public BookmarkId checkUserAndAuctionAndGetBookmarkId(String userId, Long auctionItemId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User does not exist"));

		AuctionItem auctionItem = auctionItemRepository.findById(auctionItemId)
			.orElseThrow(() -> new NonExistResourceException("Auction item does not exist"));

		return BookmarkMapper.of().requestToBookmarkId(user, auctionItem);
	}
}
