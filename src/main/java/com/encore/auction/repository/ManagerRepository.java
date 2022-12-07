package com.encore.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.auction.model.manager.Manager;

public interface ManagerRepository extends JpaRepository<Manager, String> {
}
