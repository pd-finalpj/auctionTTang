package com.encore.auction.controller.comment.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public final class CommentUpdateRequest {

	@NotEmpty
	@Pattern(regexp = "^[0-9A-Za-z]{2,12}$")
	private final String userId;
	@NotNull
	private final Long auctionItemId;
	@Size(max = 3000)
	private final String content;

	public CommentUpdateRequest(String userId, Long auctionItemId, String content) {
		this.userId = userId;
		this.auctionItemId = auctionItemId;
		this.content = content;
	}
}
