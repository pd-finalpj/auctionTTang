package com.encore.auction.service.auction;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.auction.responses.AuctionFailedLogDetailsResponse;
import com.encore.auction.controller.auction.responses.AuctionFailedLogListResponse;
import com.encore.auction.model.auction.failed.log.AuctionFailedLog;
import com.encore.auction.repository.auction.AuctionFailedLogRepository;
import com.encore.auction.utils.mapper.AuctionFailedLogMapper;

@Service
public class AuctionFailedLogService {

	private final AuctionFailedLogRepository auctionFailedLogRepository;

	public AuctionFailedLogService(AuctionFailedLogRepository auctionFailedLogRepository) {
		this.auctionFailedLogRepository = auctionFailedLogRepository;
	}

	public AuctionFailedLogListResponse getAuctionFailedLogListByAuctionItemId(Long auctionItemId) {
		List<AuctionFailedLog> auctionFailedLogList = auctionFailedLogRepository.findByAuctionItemId(auctionItemId);

		List<AuctionFailedLogDetailsResponse> auctionFailedLogDetailsResponseList = auctionFailedLogList.stream()
			.map(e -> AuctionFailedLogMapper.of().entityToDetailsResponse(e))
			.collect(Collectors.toList());

		return AuctionFailedLogMapper.of().auctionFailedLogDetailsToListResponse(auctionFailedLogDetailsResponseList);
	}
}
