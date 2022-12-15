package com.encore.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.encore.auction.model.auction.item.AuctionItem;

@Repository
public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {
}
