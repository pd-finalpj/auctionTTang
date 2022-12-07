package com.encore.auction.model.bookmark;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {

	@EmbeddedId
	private BookmarkId bookmarkId;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public Bookmark(BookmarkId bookmarkId, Boolean state) {
		this.bookmarkId = bookmarkId;
		this.state = state;
	}

	public void deleteBookmark() {
		this.state = true;
	}
}
