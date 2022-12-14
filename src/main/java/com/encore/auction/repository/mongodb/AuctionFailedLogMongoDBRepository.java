package com.encore.auction.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.encore.auction.model.auction.failed.log.AuctionFailedLogDetails;

public interface AuctionFailedLogMongoDBRepository extends MongoRepository<AuctionFailedLogDetails, Long> {
}
