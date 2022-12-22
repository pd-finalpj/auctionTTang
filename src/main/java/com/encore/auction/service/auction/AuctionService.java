package com.encore.auction.service.auction;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.encore.auction.controller.auction.requests.AuctionCreateRequest;
import com.encore.auction.controller.auction.requests.AuctionUpdateRequest;
import com.encore.auction.controller.auction.responses.AuctionDeleteResponse;
import com.encore.auction.controller.auction.responses.AuctionDetailsResponse;
import com.encore.auction.controller.auction.responses.AuctionIdResponse;
import com.encore.auction.controller.auction.responses.AuctionRetrieveResponse;
import com.encore.auction.controller.comment.responses.CommentDetailsResponse;
import com.encore.auction.controller.imagefile.responses.ImageFileUrlResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.exception.WrongRequestException;
import com.encore.auction.model.address.Address;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.bookmark.BookmarkId;
import com.encore.auction.model.comment.Comment;
import com.encore.auction.model.imagefile.ImageFile;
import com.encore.auction.model.manager.Manager;
import com.encore.auction.model.manager.ManagerRole;
import com.encore.auction.model.user.User;
import com.encore.auction.repository.ImageFileRepository;
import com.encore.auction.repository.address.AddressRepository;
import com.encore.auction.repository.auction.AuctionRepository;
import com.encore.auction.repository.bookmark.BookmarkRepository;
import com.encore.auction.repository.comment.CommentRepository;
import com.encore.auction.repository.manager.ManagerRepository;
import com.encore.auction.repository.user.UserRepository;
import com.encore.auction.service.imagefile.ImageFileService;
import com.encore.auction.utils.mapper.AuctionMapper;
import com.encore.auction.utils.mapper.BookmarkMapper;
import com.encore.auction.utils.mapper.CommentMapper;
import com.encore.auction.utils.mapper.ImageFileMapper;
import com.encore.auction.utils.token.JwtProvider;

@Service
public class AuctionService {

	private final ManagerRepository managerRepository;
	private final AuctionRepository auctionRepository;
	private final AddressRepository addressRepository;
	private final CommentRepository commentRepository;
	private final ImageFileRepository imageFileRepository;
	private final UserRepository userRepository;
	private final BookmarkRepository bookmarkRepository;
	private final JwtProvider jwtProvider;
	private final ImageFileService imageFileService;

	public AuctionService(ManagerRepository managerRepository, AuctionRepository auctionRepository,
		AddressRepository addressRepository, CommentRepository commentRepository,
		ImageFileRepository imageFileRepository,
		UserRepository userRepository, BookmarkRepository bookmarkRepository, JwtProvider jwtProvider,
		ImageFileService imageFileService) {
		this.managerRepository = managerRepository;
		this.auctionRepository = auctionRepository;
		this.addressRepository = addressRepository;
		this.commentRepository = commentRepository;
		this.imageFileRepository = imageFileRepository;
		this.userRepository = userRepository;
		this.bookmarkRepository = bookmarkRepository;
		this.jwtProvider = jwtProvider;
		this.imageFileService = imageFileService;
	}

	@Transactional
	public AuctionIdResponse createAuctionItem(AuctionCreateRequest auctionCreateRequest, String token,
		MultipartFile[] files) {
		String managerId = jwtProvider.checkTokenIsManagerAndGetManagerID(token);

		Manager manager = checkManagerExistAndCheckRole(managerId);

		Address address = checkAddressExistAndGetAddress(auctionCreateRequest.getAddressCode());

		AuctionItem newAuctionItem = AuctionMapper.of()
			.createRequestToEntity(auctionCreateRequest, manager, address);

		AuctionItem savedAuctionItem = auctionRepository.save(newAuctionItem);

		try {
			imageFileService.storeAndSaveImageFiles(savedAuctionItem.getId(), files);
		} catch (IOException e) {
			throw new WrongRequestException(e.getMessage());
		}

		return AuctionMapper.of().entityToAuctionItemIdResponse(savedAuctionItem);
	}

	@Transactional
	public AuctionRetrieveResponse retrieveAuctionItem(String token, Long auctionItemId) {
		AuctionItem auctionItem = checkAuctionExistAndGetAuctionItem(auctionItemId);

		List<Comment> commentList = commentRepository.findByAuctionItemId(auctionItemId);

		List<CommentDetailsResponse> commentDetailsResponseList = commentList.stream()
			.map(e -> CommentMapper.of().entityToCommentDetilasResponse(e))
			.collect(Collectors.toList());

		List<ImageFile> imageFiles = imageFileRepository.findByAuctionItemId(auctionItem.getId());

		List<ImageFileUrlResponse> imageFileUrlResponses = imageFiles.stream()
			.map(e -> ImageFileMapper.of().entityToImageFileUrlResponse(e))
			.collect(Collectors.toList());

		boolean isBookmarked = checkTokenExistAndGetIsBookmarkedOrNot(token, auctionItem);

		Manager manager = managerRepository.findById(auctionItem.getManager().getId())
			.orElseThrow(() -> new NonExistResourceException("Manager does not exist"));

		auctionItem.increaseHit();

		return AuctionMapper.of()
			.entityToAuctionRetrieveResponse(auctionItem, isBookmarked, manager, commentDetailsResponseList,
				imageFileUrlResponses);
	}

	@Transactional
	public AuctionDetailsResponse updateAuctionItem(Long auctionItemId, AuctionUpdateRequest auctionupdateRequest,
		String token) {
		String managerId = jwtProvider.checkTokenIsManagerAndGetManagerID(token);

		AuctionItem auctionItem = checkAuctionExistAndGetAuctionItem(auctionItemId);

		Manager manager = checkManagerExistAndCheckRole(managerId);

		Address address = checkAddressExistAndGetAddress(auctionupdateRequest.getAddressCode());

		auctionItem.updateAuctionItem(auctionupdateRequest, manager, address);

		return AuctionMapper.of().entityToAuctionDetailsResponse(auctionItem);
	}

	@Transactional
	public AuctionDeleteResponse deleteAuctionItem(Long auctionItemId, String token) {
		String managerId = jwtProvider.checkTokenIsManagerAndGetManagerID(token);

		AuctionItem auctionItem = checkAuctionExistAndGetAuctionItem(auctionItemId);

		checkManagerExistAndCheckRole(managerId);

		auctionItem.deleteAuctionItem();

		return AuctionMapper.of().entityToAuctionDeleteResponse(auctionItem);
	}

	private Manager checkManagerExistAndCheckRole(String managerId) {
		Manager manager = managerRepository.findById(managerId)
			.orElseThrow(() -> new NonExistResourceException("Manager does not exist"));

		if (manager.getManagerRole().equals(ManagerRole.DISAPPROVAL))
			throw new WrongRequestException("You do not have permission");
		return manager;
	}

	private Address checkAddressExistAndGetAddress(String addressCode) {
		return addressRepository.findById(addressCode)
			.orElseThrow(() -> new NonExistResourceException("Address does not exist"));
	}

	private AuctionItem checkAuctionExistAndGetAuctionItem(Long auctionItemId) {
		return auctionRepository.findById(auctionItemId)
			.orElseThrow(() -> new NonExistResourceException("Auction Item does not exist"));
	}

	private User checkUserExistAndGetUser(String userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User does not exist"));
	}

	private boolean checkTokenExistAndGetIsBookmarkedOrNot(String token, AuctionItem auctionItem) {
		boolean isBookmarked = false;

		if (token != null) {
			User user = checkUserExistAndGetUser(jwtProvider.checkTokenIsUserAndGetUserID(token));
			BookmarkId bookmarkId = BookmarkMapper.of().requestToBookmarkId(user, auctionItem);
			isBookmarked = bookmarkRepository.findById(bookmarkId).isPresent();
		}

		return isBookmarked;
	}
}
