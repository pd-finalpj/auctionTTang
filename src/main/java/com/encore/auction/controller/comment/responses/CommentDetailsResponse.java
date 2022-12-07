package com.encore.auction.controller.comment.responses;

import lombok.Getter;

@Getter
public final class CommentDetailsResponse {

	private final String userId;

	private final Long auctionItemId;

	private final String content;

	public CommentDetailsResponse(String userId, Long auctionItemId, String content) {
		this.userId = userId;
		this.auctionItemId = auctionItemId;
		this.content = content;
	}
}
