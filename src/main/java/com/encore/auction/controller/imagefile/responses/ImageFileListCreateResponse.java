package com.encore.auction.controller.imagefile.responses;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class ImageFileListCreateResponse {

	@NotNull
	private final List<ImageFileCreateResponse> imageFileListCreateResponse;

	public ImageFileListCreateResponse(List<ImageFileCreateResponse> imageFileListCreateResponse) {
		this.imageFileListCreateResponse = imageFileListCreateResponse;
	}
}
