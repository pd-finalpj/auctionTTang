package com.encore.auction.model.imagefile;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.encore.auction.model.BaseEntity;
import com.encore.auction.model.auction.item.AuctionItem;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageFile extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "auction_item_id")
	private AuctionItem auctionItem;

	@Column(nullable = false, length = 50)
	private String fileName;

	@Column(nullable = false, length = 100)
	private String fileLocation;

	public ImageFile(Long id, AuctionItem auctionItem, String fileName, String fileLocation) {
		this.id = id;
		this.auctionItem = auctionItem;
		this.fileName = fileName;
		this.fileLocation = fileLocation;
	}

	@Override
	public String toString() {
		return "ImageFile{" +
			"id=" + id +
			", fileName='" + fileName + '\'' +
			", fileDirectory='" + fileLocation + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ImageFile imageFile = (ImageFile)o;
		return Objects.equals(id, imageFile.id) && Objects.equals(auctionItem, imageFile.auctionItem)
			&& Objects.equals(fileName, imageFile.fileName) && Objects.equals(fileLocation,
			imageFile.fileLocation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, fileName, fileLocation);
	}
}
