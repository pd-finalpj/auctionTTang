package com.encore.auction.repository.filtering;

import org.springframework.data.repository.CrudRepository;

import com.encore.auction.model.filtering.Filtering;

public interface FilteringRedisRepository extends CrudRepository<Filtering, String> {
}
