package com.encore.auction.controller.comment.requests;

import lombok.Getter;

@Getter
public final class CommentUpdateRequest {

	private final String userId;
	private final Long auctionItemId;
	private final String content;

	public CommentUpdateRequest(String userId, Long auctionItemId, String content) {
		this.userId = userId;
		this.auctionItemId = auctionItemId;
		this.content = content;
	}
}
