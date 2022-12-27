package com.encore.auction.controller.imagefile.responses;

import java.util.List;

import lombok.Getter;

@Getter
public class ImageFileListCreateResponse {

	private final List<String> imageFileUrlList;

	public ImageFileListCreateResponse(List<String> imageFileUrlList) {
		this.imageFileUrlList = imageFileUrlList;
	}
}
