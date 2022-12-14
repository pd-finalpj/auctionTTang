package com.encore.auction.repository.bidding.bidding;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.auction.model.bidding.bidding.Bidding;

public interface BiddingRepository extends JpaRepository<Bidding, Long> {
	Optional<Bidding> findByUserIdAndAuctionItemId(String userId, Long auctionItemId);

	List<Bidding> findByAuctionItemId(Long auctionItemId);
}
