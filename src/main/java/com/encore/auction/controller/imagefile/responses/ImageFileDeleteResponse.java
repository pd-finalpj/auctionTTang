package com.encore.auction.controller.imagefile.responses;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class ImageFileDeleteResponse {

	@NotNull
	private final boolean isDeleted;

	public ImageFileDeleteResponse(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}

