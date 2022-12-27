package com.encore.auction.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.encore.auction.model.biddingdetails.BiddingDetails;

public interface BiddingDetailsMongoDBRepository extends MongoRepository<BiddingDetails, Long> {
}
