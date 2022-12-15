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

	@Column(nullable = false, length = 100)
	private String url;

	public ImageFile(Long id, AuctionItem auctionItem, String url) {
		this.id = id;
		this.auctionItem = auctionItem;
		this.url = url;
	}

	@Override
	public String toString() {
		return "ImageFile{" +
			"id=" + id +
			", url='" + url + '\'' +
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
			&& Objects.equals(url, imageFile.url);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, url);
	}
}
