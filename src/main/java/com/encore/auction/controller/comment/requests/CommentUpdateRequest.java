package com.encore.auction.controller.comment.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public final class CommentUpdateRequest {

	@NotNull
	private final Long auctionItemId;
	@Size(max = 3000, message = "내용은 최대 3000자까지 입력 가능합니다.")
	private final String content;

	public CommentUpdateRequest(Long auctionItemId, String content) {
		this.auctionItemId = auctionItemId;
		this.content = content;
	}
}
