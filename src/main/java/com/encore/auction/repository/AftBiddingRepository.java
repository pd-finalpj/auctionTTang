package com.encore.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.auction.model.bidding.aftbidding.AftBidding;

public interface AftBiddingRepository extends JpaRepository<AftBidding, Long> {
}
