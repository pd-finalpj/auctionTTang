package com.encore.auction.controller.comment.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public final class CommentUpdateRequest {

	@NotEmpty
	@Pattern(regexp = "^[0-9A-Za-z]{2,12}$", message = "아이디는 2~12자로 영문 대소문자, 숫자만 사용할 수 있습니다.")
	private final String userId;
	@NotNull
	private final Long auctionItemId;
	@Size(max = 3000, message = "내용은 최대 3000자까지 입력 가능합니다.")
	private final String content;

	public CommentUpdateRequest(String userId, Long auctionItemId, String content) {
		this.userId = userId;
		this.auctionItemId = auctionItemId;
		this.content = content;
	}
}
