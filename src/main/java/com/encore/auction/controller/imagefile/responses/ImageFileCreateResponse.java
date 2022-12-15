package com.encore.auction.controller.imagefile.responses;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class ImageFileCreateResponse {

	@NotNull
	private final Long id;

	@NotEmpty
	private final Long auctionItemId;

	@NotEmpty
	@Size(max = 50)
	private final String fileName;

	@NotEmpty
	private final String fileExtension;

	@NotEmpty
	@Size(max = 100)
	private final String fileLocation;

	public ImageFileCreateResponse(Long id, Long auctionItemId, String fileName, String fileExtension,
		String fileLocation) {
		this.id = id;
		this.auctionItemId = auctionItemId;
		this.fileName = fileName;
		this.fileExtension = fileExtension;
		this.fileLocation = fileLocation;
	}
}
