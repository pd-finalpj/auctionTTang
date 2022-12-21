package com.encore.auction.utils.mapper;

import java.util.List;

import com.encore.auction.controller.auction.responses.AuctionFailedLogDetailsResponse;
import com.encore.auction.controller.auction.responses.AuctionFailedLogListResponse;
import com.encore.auction.model.auction.failed.log.AuctionFailedLog;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuctionFailedLogMapper {

	private static AuctionFailedLogMapper auctionFailedLogMapper = null;

	public static AuctionFailedLogMapper of() {
		if (auctionFailedLogMapper == null) {
			auctionFailedLogMapper = new AuctionFailedLogMapper();
		}
		return auctionFailedLogMapper;
	}

	public AuctionFailedLogDetailsResponse entityToDetailsResponse(AuctionFailedLog auctionFailedLog) {
		return new AuctionFailedLogDetailsResponse(auctionFailedLog.getId(), auctionFailedLog.getAuctionStartDate(),
			auctionFailedLog.getAuctionEndDate(),
			auctionFailedLog.getAppraisedValue());
	}

	public AuctionFailedLogListResponse auctionFailedLogDetailsToListResponse(
		List<AuctionFailedLogDetailsResponse> auctionFailedLogDetailsResponseList) {
		return new AuctionFailedLogListResponse(auctionFailedLogDetailsResponseList);
	}
}
