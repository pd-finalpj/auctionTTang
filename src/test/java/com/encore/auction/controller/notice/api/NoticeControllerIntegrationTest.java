package com.encore.auction.controller.notice.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.controller.notice.requests.NoticeRegisterRequest;
import com.encore.auction.controller.notice.requests.NoticeUpdateRequest;
import com.encore.auction.controller.notice.responses.NoticeDeleteResponse;
import com.encore.auction.controller.notice.responses.NoticeDetailsResponse;
import com.encore.auction.controller.notice.responses.NoticeIdResponse;

@SpringBootTest
@Transactional
class NoticeControllerIntegrationTest {

	@Autowired
	private NoticeController noticeController;

	private final String managerId = "tester2";
	private final String title = "공지사항 테스트 제목";
	private final String content = "공지사항 테스트 본문";
	private final Long noticeId = 1L;
	private final String updateTitle = "업데이트할 공지사항 제목";
	private final String updateContent = "업데이트할 공지사항 본문";

	@Test
	@DisplayName("Register Notice Integration Controller Test - Success")
	void registerNoticeIntegrationSuccess() {
		//given
		NoticeRegisterRequest noticeRegisterRequest = new NoticeRegisterRequest(managerId, title, content);
		//when
		ResponseEntity<NoticeIdResponse> noticeIdResponse = noticeController.registerNotice(noticeRegisterRequest);
		//then
		assertNotNull(noticeIdResponse.getBody().getNoticeId());
	}

	@Test
	@DisplayName("Retrieve Notice Integration Controller Test - Success")
	void retrieveNoticeIntegrationSuccess() {
		//given //when
		ResponseEntity<NoticeDetailsResponse> noticeDetailsResponse = noticeController.retrieveNotice(noticeId);
		//then
		assertNotNull(noticeDetailsResponse.getBody().getNoticeId());
	}

	@Test
	@DisplayName("Update Notice Integration Controller Test - Success")
	void updateNoticeIntegrationSuccess() {
		//given
		NoticeUpdateRequest noticeUpdateRequest = new NoticeUpdateRequest(managerId, updateTitle, updateContent);
		//when
		ResponseEntity<NoticeIdResponse> noticeIdResponse = noticeController.updateNotice(noticeId,
			noticeUpdateRequest);
		//then
		assertNotNull(noticeIdResponse.getBody().getNoticeId());
	}

	@Test
	@DisplayName("Delete Notice Integration Controller Test - Success")
	void deleteNoticeIntegrationSuccess() {
		//given //when
		ResponseEntity<NoticeDeleteResponse> noticeDeleteResponse = noticeController.deleteNotice(noticeId, managerId);
		//then
		assertTrue(noticeDeleteResponse.getBody().isState());
	}
}
