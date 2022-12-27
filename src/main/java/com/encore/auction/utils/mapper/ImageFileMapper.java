package com.encore.auction.utils.mapper;

import com.encore.auction.controller.imagefile.responses.ImageFileUrlResponse;
import com.encore.auction.model.imagefile.ImageFile;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageFileMapper {

	private static ImageFileMapper imageFileMapper = null;

	public static ImageFileMapper of() {
		if (imageFileMapper == null) {
			imageFileMapper = new ImageFileMapper();
		}
		return imageFileMapper;
	}

	public ImageFile imageFileCreateRequestsToEntity(String url) {
		return ImageFile.builder().url(url).build();
	}

	public ImageFileUrlResponse entityToImageFileUrlResponse(ImageFile e) {
		return new ImageFileUrlResponse(e.getUrl());
	}
}
