package com.encore.auction.service.bidding.bidding;

import java.util.List;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsListResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.model.user.User;
import com.encore.auction.repository.bidding.bidding.BiddingListRetrieveRepository;
import com.encore.auction.repository.user.UserRepository;
import com.encore.auction.utils.token.JwtProvider;

@Service
public class BiddingListService {

	private final UserRepository userRepository;
	private final BiddingListRetrieveRepository biddingListRetrieveRepository;
	private final JwtProvider jwtProvider;

	public BiddingListService(UserRepository userRepository,
		BiddingListRetrieveRepository biddingListRetrieveRepository,
		JwtProvider jwtProvider) {
		this.userRepository = userRepository;
		this.biddingListRetrieveRepository = biddingListRetrieveRepository;
		this.jwtProvider = jwtProvider;
	}

	public BiddingDetailsListResponse retrieveBiddingListByUserId(String token) {
		String userId = jwtProvider.checkTokenIsUserAndGetUserID(token);

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User does not exist"));

		List<BiddingDetailsResponse> biddingDetailsResponseList = biddingListRetrieveRepository.retrieveBiddingListByUserId(
			user.getId());

		return new BiddingDetailsListResponse(user.getId(), biddingDetailsResponseList);
	}
}
