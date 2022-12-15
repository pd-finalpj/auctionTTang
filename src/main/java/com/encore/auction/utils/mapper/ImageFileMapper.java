package com.encore.auction.utils.mapper;

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

	// public ImageFileCreateResponse entityToImageFileCreateResponse(ImageFile imageFile) {
	// 	return new ImageFileCreateResponse();
	// }

	public ImageFile imageFileCreateRequestsToEntity(AuctionItem auctionItem, String newFileName, String fileLocation) {
		return ImageFile.builder().auctionItem(auctionItem).fileName(newFileName).fileLocation(fileLocation).build();
	}

}
