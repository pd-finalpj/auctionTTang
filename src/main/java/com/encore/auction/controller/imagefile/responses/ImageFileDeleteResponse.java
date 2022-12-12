package com.encore.auction.controller.imagefile.responses;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class ImageFileDeleteResponse {

	@NotNull
	private final Long id;

	public ImageFileDeleteResponse(Long id) {
		this.id = id;
	}
}

