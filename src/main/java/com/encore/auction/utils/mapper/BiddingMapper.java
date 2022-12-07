package com.encore.auction.utils.mapper;

import java.time.LocalDateTime;

import com.encore.auction.controller.bidding.bidding.requests.BiddingRegisterRequest;
import com.encore.auction.controller.bidding.bidding.responses.BiddingDeleteResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingIdResponse;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.bidding.bidding.Bidding;
import com.encore.auction.model.user.User;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BiddingMapper {

	private static BiddingMapper biddingMapper = null;

	public static BiddingMapper of() {
		if (biddingMapper == null) {
			biddingMapper = new BiddingMapper();
		}
		return biddingMapper;
	}

	public Bidding registerRequestToEntity(BiddingRegisterRequest biddingRegisterRequest, User user,
		AuctionItem auctionItem, LocalDateTime requestTime) {
		return Bidding.builder()
			.user(user)
			.auctionItem(auctionItem)
			.amount(biddingRegisterRequest.getAmount())
			.biddingDate(requestTime)
			.build();
	}

	public BiddingIdResponse entityToBiddingIdResponse(Bidding savedBidding) {
		return new BiddingIdResponse(savedBidding.getId());
	}

	public BiddingDeleteResponse entityToBiddingDeleteResponse(Bidding bidding) {
		return new BiddingDeleteResponse(bidding.getId(), bidding.getState());
	}

	public BiddingDetailsResponse entityToBiddingDetailsResponse(Bidding bidding) {
		return new BiddingDetailsResponse(bidding.getId(), bidding.getAuctionItem().getId(), bidding.getAuctionItem()
			.getAuctionItemName(), bidding.getBiddingDate(), bidding.getAmount());
	}
}
