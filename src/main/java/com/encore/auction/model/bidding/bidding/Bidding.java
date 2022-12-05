package com.encore.auction.model.bidding.bidding;

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

import org.hibernate.annotations.Where;

import com.encore.auction.model.BaseEntity;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.user.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "state = false")
public class Bidding extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "auction_item_id")
	private AuctionItem auctionItem;

	@Column(nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private LocalDateTime biddingDate;

	@Column(nullable = false)
	private Long amount;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public Bidding(Long id, User user, AuctionItem auctionItem, LocalDateTime biddingDate, Long amount, Boolean state) {
		this.id = id;
		this.user = user;
		this.auctionItem = auctionItem;
		this.biddingDate = biddingDate;
		this.amount = amount;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Bidding{" +
			"id=" + id +
			", biddingDate=" + biddingDate +
			", amount=" + amount +
			", state=" + state +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Bidding bidding = (Bidding)o;
		return Objects.equals(id, bidding.id) && Objects.equals(user, bidding.user)
			&& Objects.equals(auctionItem, bidding.auctionItem) && Objects.equals(biddingDate,
			bidding.biddingDate) && Objects.equals(amount, bidding.amount) && Objects.equals(state,
			bidding.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, biddingDate, amount, state);
	}
}
