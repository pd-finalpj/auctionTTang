package com.encore.auction.controller.imagefile.responses;

import lombok.Getter;

@Getter
public final class ImageFileUrlResponse {
	
	private final String url;

	public ImageFileUrlResponse(String url) {
		this.url = url;
	}
}
