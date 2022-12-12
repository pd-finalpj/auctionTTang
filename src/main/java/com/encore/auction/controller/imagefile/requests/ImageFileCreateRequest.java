package com.encore.auction.controller.imagefile.requests;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.encore.auction.model.auction.item.AuctionItem;

import lombok.Getter;

@Getter
public class ImageFileCreateRequest {

	@NotNull
	private final Long id;

	@NotEmpty
	private final AuctionItem auctionItem;

	@NotEmpty
	private final String fileName;

	@NotEmpty
	private final String fileDirectory;

	public ImageFileCreateRequest(Long id, AuctionItem auctionItem, String fileName, String fileDirectory) {
		this.id = id;
		this.auctionItem = auctionItem;
		this.fileName = fileName;
		this.fileDirectory = fileDirectory;
	}
}
