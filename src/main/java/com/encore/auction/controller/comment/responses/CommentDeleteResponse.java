package com.encore.auction.controller.comment.responses;

import lombok.Getter;

@Getter
public final class CommentDeleteResponse {

	private final Long commentId;
	private final boolean state;

	public CommentDeleteResponse(Long commentId, boolean state) {
		this.commentId = commentId;
		this.state = state;
	}
}
