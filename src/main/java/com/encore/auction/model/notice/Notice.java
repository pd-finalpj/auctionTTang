package com.encore.auction.model.notice;

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
import com.encore.auction.model.manager.Manager;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "state = false")
public class Notice extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "manager_id")
	private Manager manager;

	@Column(nullable = false, length = 50)
	private String title;

	@Column(nullable = false, length = 3000)
	private String content;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public Notice(Long id, Manager manager, String title, String content, Boolean state) {
		this.id = id;
		this.manager = manager;
		this.title = title;
		this.content = content;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Notice{" +
			"id=" + id +
			", title='" + title + '\'' +
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
		Notice notice = (Notice)o;
		return Objects.equals(id, notice.id) && Objects.equals(manager, notice.manager)
			&& Objects.equals(title, notice.title) && Objects.equals(content, notice.content)
			&& Objects.equals(state, notice.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, content, state);
	}
}
