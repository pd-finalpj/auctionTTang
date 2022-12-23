package com.encore.auction.repository.bookmark;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.auction.model.bookmark.Bookmark;
import com.encore.auction.model.bookmark.BookmarkId;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId> {
	List<Bookmark> findByUserId(String id);

	List<Bookmark> findByBookmarkIdUserId(String id);
}
