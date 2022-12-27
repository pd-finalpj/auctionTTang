package com.encore.auction.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.auction.model.address.Address;

public interface AddressRepository extends JpaRepository<Address, String> {
}
