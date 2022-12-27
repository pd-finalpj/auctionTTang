package com.encore.auction.model.auction.failed.log;

import java.time.LocalDateTime;
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
public class AuctionFailedLog extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "auction_item_id")
	private AuctionItem auctionItem;

	@Column(nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime auctionStartDate;

	@Column(nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime auctionEndDate;

	@Column(nullable = false)
	private Long appraisedValue;

	public AuctionFailedLog(Long id, AuctionItem auctionItem, LocalDateTime auctionStartDate,
		LocalDateTime auctionEndDate,
		Long appraisedValue) {
		this.id = id;
		this.auctionItem = auctionItem;
		this.auctionStartDate = auctionStartDate;
		this.auctionEndDate = auctionEndDate;
		this.appraisedValue = appraisedValue;
	}

	@Override
	public String toString() {
		return "AuctionFailedLog{" +
			"id=" + id +
			", auctionStartDate=" + auctionStartDate +
			", auctionEndDate=" + auctionEndDate +
			", appraisedValue=" + appraisedValue +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AuctionFailedLog that = (AuctionFailedLog)o;
		return Objects.equals(id, that.id) && Objects.equals(auctionItem, that.auctionItem)
			&& Objects.equals(auctionStartDate, that.auctionStartDate) && Objects.equals(auctionEndDate,
			that.auctionEndDate) && Objects.equals(appraisedValue, that.appraisedValue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, auctionStartDate, auctionEndDate, appraisedValue);
	}
}
