package com.encore.auction.service.bidding.bidding;

import java.util.List;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsListResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.model.user.User;
import com.encore.auction.repository.BiddingListRetrieveRepository;
import com.encore.auction.repository.UserRepository;

@Service
public class BiddingListService {

	private final UserRepository userRepository;
	private final BiddingListRetrieveRepository biddingListRetrieveRepository;

	public BiddingListService(UserRepository userRepository,
		BiddingListRetrieveRepository biddingListRetrieveRepository) {
		this.userRepository = userRepository;
		this.biddingListRetrieveRepository = biddingListRetrieveRepository;
	}

	public BiddingDetailsListResponse retrieveBiddingListByUserId(String userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User does not exist"));

		List<BiddingDetailsResponse> biddingDetailsResponseList = biddingListRetrieveRepository.retrieveBiddingListByUserId(
			user.getId());

		return new BiddingDetailsListResponse(user.getId(), biddingDetailsResponseList);
	}
}
