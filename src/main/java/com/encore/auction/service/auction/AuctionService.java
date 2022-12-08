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
		Manager manager = managerRepository.findById(auctionCreateRequest.getManagerId())
			.orElseThrow(() -> new NonExistResourceException("Manager does not exist"));

		if (manager.getManagerRole().equals(ManagerRole.DISAPPROVAL))
			throw new WrongRequestException("You do not have permission");

		Address address = addressRepository.findById(auctionCreateRequest.getAddressCode())
			.orElseThrow(() -> new NonExistResourceException("Address does not exist"));

		AuctionItem newAuctionItem = AuctionMapper.of()
			.createRequestToEntity(auctionCreateRequest, manager, address);

		AuctionItem savedAuctionItem = auctionRepository.save(newAuctionItem);

		return AuctionMapper.of().entityToAuctionItemIdResponse(savedAuctionItem);
	}

	public AuctionRetrieveResponse retrieveAuctionItem(Long auctionItemId) {
		AuctionItem auctionItem = auctionRepository.findById(auctionItemId)
			.orElseThrow(() -> new NonExistResourceException("Auction Item does not exist"));

		List<Comment> commentList = commentRepository.findByAuctionItemId(auctionItemId);

		List<CommentDetailsResponse> commentDetailsResponseList = commentList.stream()
			.map(e -> CommentMapper.of().entityToCommentDetilasResponse(e))
			.collect(Collectors.toList());

		return AuctionMapper.of().entityToAuctionRetrieveResponse(auctionItem, commentDetailsResponseList);
	}

	@Transactional
	public AuctionDetailsResponse updateAuctionItem(Long auctionItemId, AuctionUpdateRequest auctionupdateRequest) {
		AuctionItem auctionItem = auctionRepository.findById(auctionItemId)
			.orElseThrow(() -> new NonExistResourceException("Auction Item does not exist"));

		Manager manager = managerRepository.findById(auctionupdateRequest.getManagerId())
			.orElseThrow(() -> new NonExistResourceException("Manager does not exist"));

		if (manager.getManagerRole().equals(ManagerRole.DISAPPROVAL))
			throw new WrongRequestException("You do not have permission");

		Address address = addressRepository.findById(auctionupdateRequest.getAddressCode())
			.orElseThrow(() -> new NonExistResourceException("Address does not exist"));

		auctionItem.updateAuctionItem(auctionupdateRequest, manager, address);

		return AuctionMapper.of().entityToAuctionDetailsResponse(auctionItem);
	}

	@Transactional
	public AuctionDeleteResponse deleteAuctionItem(Long auctionItemId, String managerId) {
		AuctionItem auctionItem = auctionRepository.findById(auctionItemId)
			.orElseThrow(() -> new NonExistResourceException("Auction Item does not exist"));

		Manager manager = managerRepository.findById(managerId)
			.orElseThrow(() -> new NonExistResourceException("Manager does not exist"));

		if (manager.getManagerRole().equals(ManagerRole.DISAPPROVAL))
			throw new WrongRequestException("You do not have permission");

		auctionItem.deleteAuctionItem();

		return AuctionMapper.of().entityToAuctionDeleteResponse(auctionItem);
	}
}
