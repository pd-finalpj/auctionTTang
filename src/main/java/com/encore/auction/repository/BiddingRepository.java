package com.encore.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.auction.model.bidding.bidding.Bidding;

public interface BiddingRepository extends JpaRepository<Bidding, Long> {
}
