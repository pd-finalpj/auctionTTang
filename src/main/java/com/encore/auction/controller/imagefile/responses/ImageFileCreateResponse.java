package com.encore.auction.controller.imagefile.responses;

import lombok.Getter;

@Getter
public class ImageFileCreateResponse {

	private final Long id;

	private final Long auctionItemId;

	private final String url;

	public ImageFileCreateResponse(Long id, Long auctionItemId, String url) {
		this.id = id;
		this.auctionItemId = auctionItemId;
		this.url = url;
	}
}
