package com.encore.auction.controller.bookmark.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public final class BookmarkRegisterRequest {

	@NotEmpty
	@Pattern(regexp = "^[0-9A-Za-z]{2,12}$")
	private final String userId;
	@NotNull
	private final Long auctionId;

	public BookmarkRegisterRequest(String userId, Long auctionId) {
		this.userId = userId;
		this.auctionId = auctionId;
	}
}
