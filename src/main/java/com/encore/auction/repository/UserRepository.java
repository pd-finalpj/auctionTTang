package com.encore.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.auction.model.user.User;

public interface UserRepository extends JpaRepository<User, String> {
}
