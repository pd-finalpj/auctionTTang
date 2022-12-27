package com.encore.auction.repository.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encore.auction.model.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByAuctionItemId(Long auctionItemId);
}
