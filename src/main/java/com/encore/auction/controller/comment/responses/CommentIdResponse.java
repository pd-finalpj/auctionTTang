package com.encore.auction.controller.comment.responses;

import lombok.Getter;

@Getter
public final class CommentIdResponse {

	private final Long commentId;

	public CommentIdResponse(Long commentId) {
		this.commentId = commentId;
	}
}
