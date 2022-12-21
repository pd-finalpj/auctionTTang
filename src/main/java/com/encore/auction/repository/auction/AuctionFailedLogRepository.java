package com.encore.auction.repository.auction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.auction.model.auction.failed.log.AuctionFailedLog;

public interface AuctionFailedLogRepository extends JpaRepository<AuctionFailedLog, Long> {
	List<AuctionFailedLog> findByAuctionItemId(Long auctionItemId);
}
