package com.encore.auction.utils.mapper;

import com.encore.auction.controller.imagefile.responses.ImageFileCreateResponse;
import com.encore.auction.model.auction.item.AuctionItem;
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

	public ImageFile imageFileCreateRequestsToEntity(AuctionItem auctionItem, String url) {
		return ImageFile.builder().auctionItem(auctionItem).url(url).build();
	}

	public ImageFileCreateResponse entityToImageFileResponse(ImageFile savedImageFile) {
		return new ImageFileCreateResponse(savedImageFile.getId(), savedImageFile.getAuctionItem().getId(),
			savedImageFile.getUrl());
	}
}
