package com.encore.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.auction.model.bookmark.Bookmark;
import com.encore.auction.model.bookmark.BookmarkId;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId> {
}
