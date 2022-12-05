package com.encore.auction.model.comment;

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
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "auction_item_id")
	private AuctionItem auctionItem;

	@Column(nullable = false, length = 3000)
	private String content;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public Comment(Long id, User user, AuctionItem auctionItem, String content, Boolean state) {
		this.id = id;
		this.user = user;
		this.auctionItem = auctionItem;
		this.content = content;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Comment{" +
			"id=" + id +
			", content='" + content + '\'' +
			", state=" + state +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Comment comment = (Comment)o;
		return Objects.equals(id, comment.id) && Objects.equals(user, comment.user)
			&& Objects.equals(auctionItem, comment.auctionItem) && Objects.equals(content,
			comment.content) && Objects.equals(state, comment.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, content, state);
	}
}
