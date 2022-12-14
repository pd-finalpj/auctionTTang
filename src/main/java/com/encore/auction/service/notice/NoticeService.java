package com.encore.auction.service.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.controller.notice.requests.NoticeRegisterRequest;
import com.encore.auction.controller.notice.requests.NoticeRetrieveRequest;
import com.encore.auction.controller.notice.requests.NoticeUpdateRequest;
import com.encore.auction.controller.notice.responses.NoticeDeleteResponse;
import com.encore.auction.controller.notice.responses.NoticeDetailsListResponse;
import com.encore.auction.controller.notice.responses.NoticeDetailsResponse;
import com.encore.auction.controller.notice.responses.NoticeIdResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.exception.WrongRequestException;
import com.encore.auction.model.manager.Manager;
import com.encore.auction.model.manager.ManagerRole;
import com.encore.auction.model.notice.Notice;
import com.encore.auction.model.notice.QNotice;
import com.encore.auction.repository.manager.ManagerRepository;
import com.encore.auction.repository.notice.NoticeRepository;
import com.encore.auction.utils.mapper.NoticeMapper;
import com.encore.auction.utils.token.JwtProvider;
import com.querydsl.core.BooleanBuilder;

@Service
public class NoticeService {

	private final NoticeRepository noticeRepository;
	private final ManagerRepository managerRepository;
	private final JwtProvider jwtProvider;

	public NoticeService(NoticeRepository noticeRepository, ManagerRepository managerRepository,
		JwtProvider jwtProvider) {
		this.noticeRepository = noticeRepository;
		this.managerRepository = managerRepository;
		this.jwtProvider = jwtProvider;
	}

	@Transactional
	public NoticeIdResponse registerNotice(String token, NoticeRegisterRequest noticeRegisterRequest) {
		String managerId = checkTokenIsManagerAndGetManagerID(token);

		Manager manager = checkManagerExistAndRoleAndGetManager(managerId);

		Notice notice = NoticeMapper.of().registerRequestToEntity(noticeRegisterRequest, manager);

		Notice savedNotice = noticeRepository.save(notice);

		return NoticeMapper.of().entityToNoticeIdResponse(savedNotice);
	}

	public NoticeDetailsResponse retrieveNotice(Long noticeId) {
		Notice notice = checkNoticeExistAndGetNotice(noticeId);

		return NoticeMapper.of().entityToNoticeDetailsResponse(notice);
	}

	@Transactional
	public NoticeIdResponse updateNotice(Long noticeId, String token, NoticeUpdateRequest noticeUpdateRequest) {
		String managerId = checkTokenIsManagerAndGetManagerID(token);

		Manager manager = checkManagerExistAndRoleAndGetManager(managerId);

		Notice notice = checkNoticeExistAndGetNotice(noticeId);

		notice.updateNotice(noticeUpdateRequest, manager);

		return NoticeMapper.of().entityToNoticeIdResponse(notice);
	}

	@Transactional
	public NoticeDeleteResponse deleteNotice(Long noticeId, String token) {
		String managerId = checkTokenIsManagerAndGetManagerID(token);

		Manager manager = checkManagerExistAndRoleAndGetManager(managerId);

		Notice notice = checkNoticeExistAndGetNotice(noticeId);

		notice.deleteNotice(manager);

		return NoticeMapper.of().entityToNoticeDeleteResponse(notice);
	}

	private Manager checkManagerExistAndRoleAndGetManager(String managerId) {
		Manager manager = managerRepository.findById(managerId)
			.orElseThrow(() -> new NonExistResourceException("Manager does not exist"));

		if (manager.getManagerRole().equals(ManagerRole.DISAPPROVAL))
			throw new WrongRequestException("Manager Disapproval");

		return manager;
	}

	private Notice checkNoticeExistAndGetNotice(Long noticeId) {
		return noticeRepository.findById(noticeId)
			.orElseThrow(() -> new NonExistResourceException("Notice does not exit"));
	}

	private String checkTokenIsManagerAndGetManagerID(String token) {
		if (jwtProvider.getAudience(token).equals("user"))
			throw new WrongRequestException("User Token can't do manager's thing");
		return jwtProvider.getSubject(token);
	}

	public NoticeDetailsListResponse retrieveNoticeList(NoticeRetrieveRequest noticeRetrieveRequest) {
		int pageNum = noticeRetrieveRequest.getPageNum() != null ? noticeRetrieveRequest.getPageNum() : 1;
		int amount = noticeRetrieveRequest.getAmount() != null ? noticeRetrieveRequest.getAmount() : 10;

		QNotice qNotice = QNotice.notice;

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		booleanBuilder.and(qNotice.state.isFalse());

		PageRequest pageRequest = PageRequest.of(pageNum - 1, amount, Sort.by("id").descending());

		Page<Notice> noticeList = noticeRepository.findAll(booleanBuilder, pageRequest);

		return NoticeMapper.of().noticeListToDetailsListResponse(noticeList);
	}
}
