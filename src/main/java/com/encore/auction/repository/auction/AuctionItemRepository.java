package com.encore.auction.repository.auction;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.auction.model.auction.item.AuctionItem;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {
}
