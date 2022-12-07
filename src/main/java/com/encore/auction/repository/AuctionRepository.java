package com.encore.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.auction.model.auction.item.AuctionItem;

public interface AuctionRepository extends JpaRepository<AuctionItem, Long> {
}
