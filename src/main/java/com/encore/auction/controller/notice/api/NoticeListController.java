package com.encore.auction.controller.notice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.notice.requests.NoticeRetrieveRequest;
import com.encore.auction.controller.notice.responses.NoticeDetailsListResponse;
import com.encore.auction.service.notice.NoticeService;

@RestController
@RequestMapping("/notice-list")
public class NoticeListController {

	private final NoticeService noticeService;

	public NoticeListController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@GetMapping
	public ResponseEntity<NoticeDetailsListResponse> retrieveNoticeList(
		@ModelAttribute NoticeRetrieveRequest noticeRetrieveRequest) {
		return ResponseEntity.ok().body(noticeService.retrieveNoticeList(noticeRetrieveRequest));
	}
}
