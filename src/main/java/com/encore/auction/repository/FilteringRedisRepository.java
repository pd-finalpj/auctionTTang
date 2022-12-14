package com.encore.auction.repository;

import org.springframework.data.repository.CrudRepository;

import com.encore.auction.model.filtering.Filtering;

public interface FilteringRedisRepository extends CrudRepository<Filtering, String> {
}
