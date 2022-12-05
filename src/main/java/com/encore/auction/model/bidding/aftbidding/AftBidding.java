package com.encore.auction.model.bidding.aftbidding;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.encore.auction.model.BaseEntity;
import com.encore.auction.model.bidding.bidding.Bidding;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AftBidding extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "bidding_id")
	private Bidding bidding;

	@Column(nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime decideDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BiddingResult biddingResult;

	public AftBidding(Long id, Bidding bidding, LocalDateTime decideDate, BiddingResult biddingResult) {
		this.id = id;
		this.bidding = bidding;
		this.decideDate = decideDate;
		this.biddingResult = biddingResult;
	}

	@Override
	public String toString() {
		return "AftBidding{" +
			"id=" + id +
			", decideDate=" + decideDate +
			", biddingResult=" + biddingResult +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AftBidding that = (AftBidding)o;
		return Objects.equals(id, that.id) && Objects.equals(bidding, that.bidding)
			&& Objects.equals(decideDate, that.decideDate) && biddingResult == that.biddingResult;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, decideDate, biddingResult);
	}
}
