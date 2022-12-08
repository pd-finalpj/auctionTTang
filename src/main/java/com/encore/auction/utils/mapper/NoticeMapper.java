package com.encore.auction.utils.mapper;

import com.encore.auction.controller.notice.requests.NoticeRegisterRequest;
import com.encore.auction.controller.notice.responses.NoticeDeleteResponse;
import com.encore.auction.controller.notice.responses.NoticeDetailsResponse;
import com.encore.auction.controller.notice.responses.NoticeIdResponse;
import com.encore.auction.model.manager.Manager;
import com.encore.auction.model.notice.Notice;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NoticeMapper {

	private static NoticeMapper noticeMapper = null;

	public static NoticeMapper of() {
		if (noticeMapper == null) {
			noticeMapper = new NoticeMapper();
		}
		return noticeMapper;
	}

	public Notice registerRequestToEntity(NoticeRegisterRequest noticeRegisterRequest, Manager manager) {
		return Notice.builder()
			.title(noticeRegisterRequest.getTitle())
			.manager(manager)
			.content(noticeRegisterRequest.getContent())
			.state(false)
			.build();
	}

	public NoticeIdResponse entityToNoticeIdResponse(Notice savedNotice) {
		return new NoticeIdResponse(savedNotice.getId());
	}

	public NoticeDetailsResponse entityToNoticeDetailsResponse(Notice notice) {
		return new NoticeDetailsResponse(notice.getId(), notice.getManager().getId(), notice.getManager().getName(),
			notice.getTitle(), notice.getContent());
	}

	public NoticeDeleteResponse entityToNoticeDeleteResponse(Notice notice) {
		return new NoticeDeleteResponse(notice.getId(), notice.getState());
	}
}
