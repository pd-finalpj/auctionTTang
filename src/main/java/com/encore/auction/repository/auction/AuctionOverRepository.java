package com.encore.auction.repository.auction;

import java.time.LocalDateTime;
import java.util.List;

import com.encore.auction.controller.auction.responses.AuctionOverDateResponse;

public interface AuctionOverRepository {

	public List<AuctionOverDateResponse> auctionOverListByStartDateAndEndDate(LocalDateTime now);
}
