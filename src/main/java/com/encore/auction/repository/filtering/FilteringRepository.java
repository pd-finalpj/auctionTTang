package com.encore.auction.repository.filtering;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.auction.model.auction.item.AuctionItem;

public interface FilteringRepository extends JpaRepository<AuctionItem, Long> {
}
