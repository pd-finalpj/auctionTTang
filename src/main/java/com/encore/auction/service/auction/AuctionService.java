package com.encore.auction.service.auction;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.controller.auction.requests.AuctionCreateRequest;
import com.encore.auction.controller.auction.requests.AuctionUpdateRequest;
import com.encore.auction.controller.auction.responses.AuctionDeleteResponse;
import com.encore.auction.controller.auction.responses.AuctionDetailsResponse;
import com.encore.auction.controller.auction.responses.AuctionIdResponse;
import com.encore.auction.controller.auction.responses.AuctionRetrieveResponse;
import com.encore.auction.controller.comment.responses.CommentDetailsResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.exception.WrongRequestException;
import com.encore.auction.model.address.Address;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.comment.Comment;
import com.encore.auction.model.manager.Manager;
import com.encore.auction.model.manager.ManagerRole;
import com.encore.auction.repository.AddressRepository;
import com.encore.auction.repository.AuctionRepository;
import com.encore.auction.repository.CommentRepository;
import com.encore.auction.repository.ManagerRepository;
import com.encore.auction.utils.mapper.AuctionMapper;
import com.encore.auction.utils.mapper.CommentMapper;

@Service
public class AuctionService {

	private final ManagerRepository managerRepository;
	private final AuctionRepository auctionRepository;
	private final AddressRepository addressRepository;
	private final CommentRepository commentRepository;

	public AuctionService(ManagerRepository managerRepository, AuctionRepository auctionRepository,
		AddressRepository addressRepository, CommentRepository commentRepository) {
		this.managerRepository = managerRepository;
		this.auctionRepository = auctionRepository;
		this.addressRepository = addressRepository;
		this.commentRepository = commentRepository;
	}

	@Transactional
	public AuctionIdResponse createAuctionItem(AuctionCreateRequest auctionCreateRequest) {
		Manager manager = checkManagerExistAndCheckRole(auctionCreateRequest.getManagerId());

		Address address = checkAddressExistAndGetAddress(auctionCreateRequest.getAddressCode());

		AuctionItem newAuctionItem = AuctionMapper.of()
			.createRequestToEntity(auctionCreateRequest, manager, address);

		AuctionItem savedAuctionItem = auctionRepository.save(newAuctionItem);

		return AuctionMapper.of().entityToAuctionItemIdResponse(savedAuctionItem);
	}

	@Transactional
	public AuctionRetrieveResponse retrieveAuctionItem(Long auctionItemId) {
		AuctionItem auctionItem = checkAuctionExistAndGetAuctionItem(auctionItemId);

		List<Comment> commentList = commentRepository.findByAuctionItemId(auctionItemId);

		List<CommentDetailsResponse> commentDetailsResponseList = commentList.stream()
			.map(e -> CommentMapper.of().entityToCommentDetilasResponse(e))
			.collect(Collectors.toList());

		auctionItem.increaseHit();

		return AuctionMapper.of().entityToAuctionRetrieveResponse(auctionItem, commentDetailsResponseList);
	}

	@Transactional
	public AuctionDetailsResponse updateAuctionItem(Long auctionItemId, AuctionUpdateRequest auctionupdateRequest) {
		AuctionItem auctionItem = checkAuctionExistAndGetAuctionItem(auctionItemId);

		Manager manager = checkManagerExistAndCheckRole(auctionupdateRequest.getManagerId());

		Address address = checkAddressExistAndGetAddress(auctionupdateRequest.getAddressCode());

		auctionItem.updateAuctionItem(auctionupdateRequest, manager, address);

		return AuctionMapper.of().entityToAuctionDetailsResponse(auctionItem);
	}

	@Transactional
	public AuctionDeleteResponse deleteAuctionItem(Long auctionItemId, String managerId) {
		AuctionItem auctionItem = checkAuctionExistAndGetAuctionItem(auctionItemId);

		Manager manager = checkManagerExistAndCheckRole(managerId);

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
}
