package com.encore.auction.service.notice;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.notice.requests.NoticeRegisterRequest;
import com.encore.auction.controller.notice.requests.NoticeUpdateRequest;
import com.encore.auction.controller.notice.responses.NoticeDeleteResponse;
import com.encore.auction.controller.notice.responses.NoticeDetailsResponse;
import com.encore.auction.controller.notice.responses.NoticeIdResponse;

@Service
public class NoticeService {
	public NoticeIdResponse registerNotice(NoticeRegisterRequest noticeRegisterRequest) {
		return null;
	}

	public NoticeDetailsResponse retrieveNotice(Long noticeId) {
		return null;
	}

	public NoticeIdResponse updateNotice(Long noticeId, NoticeUpdateRequest noticeUpdateRequest) {
		return null;
	}

	public NoticeDeleteResponse deleteNotice(Long noticeId, String managerId) {
		return null;
	}
}
