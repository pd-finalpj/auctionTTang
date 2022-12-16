package com.encore.auction.service.bidding.aftbidding;

import java.util.List;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.bidding.aftbidding.responses.AftBiddingDetailsListResponse;
import com.encore.auction.controller.bidding.aftbidding.responses.AftBiddingDetailsResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.model.user.User;
import com.encore.auction.repository.bidding.aftbidding.AftBiddingRetrieveRepository;
import com.encore.auction.repository.user.UserRepository;
import com.encore.auction.utils.token.JwtProvider;

@Service
public class AftBiddingListService {

	private final UserRepository userRepository;
	private final AftBiddingRetrieveRepository aftBiddingRetrieveRepository;
	private final JwtProvider jwtProvider;

	public AftBiddingListService(UserRepository userRepository,
		AftBiddingRetrieveRepository aftBiddingRetrieveRepository,
		JwtProvider jwtProvider) {
		this.userRepository = userRepository;
		this.aftBiddingRetrieveRepository = aftBiddingRetrieveRepository;
		this.jwtProvider = jwtProvider;
	}

	public AftBiddingDetailsListResponse retrieveAftBiddingListByUserId(String token) {
		String userId = jwtProvider.checkTokenIsUserAndGetUserID(token);

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User does not exist"));

		List<AftBiddingDetailsResponse> aftBiddingDetailsResponseList = aftBiddingRetrieveRepository.retrieveAftBiddingListByUserId(
			user.getId());

		return new AftBiddingDetailsListResponse(user.getId(), aftBiddingDetailsResponseList);
	}
}
