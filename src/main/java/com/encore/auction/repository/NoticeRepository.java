package com.encore.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.auction.model.notice.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
