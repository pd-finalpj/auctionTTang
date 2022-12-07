package com.encore.auction.service.auction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.controller.auction.requests.AuctionCreateRequest;
import com.encore.auction.controller.auction.requests.AuctionUpdateRequest;
import com.encore.auction.controller.auction.responses.AuctionDeleteResponse;
import com.encore.auction.controller.auction.responses.AuctionDetailsResponse;
import com.encore.auction.controller.auction.responses.AuctionIdResponse;
import com.encore.auction.repository.AddressRepository;
import com.encore.auction.repository.AuctionRepository;
import com.encore.auction.repository.ManagerRepository;

@Service
public class AuctionService {

	private final ManagerRepository managerRepository;
	private final AuctionRepository auctionRepository;
	private final AddressRepository addressRepository;

	public AuctionService(ManagerRepository managerRepository, AuctionRepository auctionRepository,
		AddressRepository addressRepository) {
		this.managerRepository = managerRepository;
		this.auctionRepository = auctionRepository;
		this.addressRepository = addressRepository;
	}

	@Transactional
	public AuctionIdResponse createAuctionItem(AuctionCreateRequest auctionCreateRequest) {
		// Manager manager = managerRepository.findById(auctionCreateRequest.getManagerId())
		// 	.orElseThrow(() -> new NonExistResourceException("Manager does not exist"));
		//
		// if (manager.getManagerRole().equals(ManagerRole.DISAPPROVAL))
		// 	throw new WrongRequestException("You do not have permission");
		//
		// AuctionItem newAuctionItem = AuctionMapper.of()
		// 	.createRequestEntity(auctionCreateRequest, manager, );
		//
		// AuctionItem savedAuctionItem = auctionRepository.save(newAuctionItem);
		//
		// AuctionMapper.of().entityAuctionItemResponse(savedAuctionItem);

		return null;
	}

	public AuctionDetailsResponse retrieveAuctionItem(Long s) {
		return null;
	}

	@Transactional
	public AuctionDetailsResponse updateAuctionItem(Long auctionItemId, AuctionUpdateRequest auctionupdateRequest) {
		return null;
	}

	@Transactional
	public AuctionDeleteResponse deleteAuctionItem(Long auctionItemId, String managerId) {
		return null;
	}
}
