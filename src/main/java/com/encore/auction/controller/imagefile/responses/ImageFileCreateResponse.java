package com.encore.auction.controller.imagefile.responses;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.encore.auction.model.auction.item.AuctionItem;

import lombok.Getter;

@Getter
public class ImageFileCreateResponse {

	@NotNull
	private final Long id;

	@NotEmpty
	private final AuctionItem auctionItem;

	@NotEmpty
	private final String fileName;

	@NotEmpty
	private final String fileDirectory;

	public ImageFileCreateResponse(Long id, AuctionItem auctionItem, String fileName, String fileDirectory) {
		this.id = id;
		this.auctionItem = auctionItem;
		this.fileName = fileName;
		this.fileDirectory = fileDirectory;
	}
}
