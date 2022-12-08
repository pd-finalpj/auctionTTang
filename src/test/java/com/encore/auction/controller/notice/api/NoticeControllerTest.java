package com.encore.auction.controller.notice.api;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.encore.auction.controller.notice.requests.NoticeRegisterRequest;
import com.encore.auction.controller.notice.requests.NoticeUpdateRequest;
import com.encore.auction.controller.notice.responses.NoticeDeleteResponse;
import com.encore.auction.controller.notice.responses.NoticeDetailsResponse;
import com.encore.auction.controller.notice.responses.NoticeIdResponse;
import com.encore.auction.service.notice.NoticeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = NoticeController.class)
@AutoConfigureRestDocs
class NoticeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NoticeService noticeService;

	@Autowired
	private ObjectMapper objectMapper;

	private final String managerId = "manager1";
	private final String managerName = "정정일";
	private final Long noticeId = 3L;
	private final String title = "인기있는 매물들은 어떤 특징을 가지고 있을까요?";
	private final String content = "이러이러 해서 이러이러 해요";

	@Test
	@DisplayName("Register Notice Controller Test - Success")
	void registerNoticeSuccess() throws Exception {
		//given
		NoticeRegisterRequest noticeRegisterRequest = new NoticeRegisterRequest(managerId, title, content);
		NoticeIdResponse noticeIdResponse = new NoticeIdResponse(noticeId);

		when(noticeService.registerNotice(any(NoticeRegisterRequest.class))).thenReturn(noticeIdResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			post("/notice")
				.content(objectMapper.writeValueAsString(noticeRegisterRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isCreated())
			.andDo(print())
			.andDo(document("notice-register",
				requestFields(
					fieldWithPath("managerId").type(JsonFieldType.STRING).description("공지사항을 작성할 Manager Id"),
					fieldWithPath("title").type(JsonFieldType.STRING).description("공지사항 타이틀"),
					fieldWithPath("content").type(JsonFieldType.STRING).description("공지사항 본문")
				),
				responseFields(
					fieldWithPath("noticeId").type(JsonFieldType.NUMBER).description("생성된 공지사항 Id")
				)
			));
	}

	@Test
	@DisplayName("Retrieve Notice Controller Test - Success")
	void retrieveNoticeSuccess() throws Exception {
		//given
		NoticeDetailsResponse noticeDetailsResponse = new NoticeDetailsResponse(noticeId, managerId, managerName, title,
			content);

		when(noticeService.retrieveNotice(anyLong())).thenReturn(noticeDetailsResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			get("/notice/{notice-id}", noticeId)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("retrieve-notice",
				pathParameters(parameterWithName("notice-id").description("찾고 싶은 공지사항 id")),
				responseFields(
					fieldWithPath("noticeId").type(JsonFieldType.NUMBER).description("공지사항 아이디"),
					fieldWithPath("managerId").type(JsonFieldType.STRING).description("공지사항을 작성한 매니저 아이디"),
					fieldWithPath("managerName").type(JsonFieldType.STRING).description("공지사항을 작성한 매니저 이름"),
					fieldWithPath("title").type(JsonFieldType.STRING).description("공지사항 제목"),
					fieldWithPath("content").type(JsonFieldType.STRING).description("공지사항 본문")
				)
			));
	}

	@Test
	@DisplayName("Update Notice Controller Test - Success")
	void updateNoticeSuccess() throws Exception {
		//given
		NoticeUpdateRequest noticeUpdateRequest = new NoticeUpdateRequest(managerId, title, content);
		NoticeIdResponse noticeIdResponse = new NoticeIdResponse(noticeId);

		when(noticeService.updateNotice(anyLong(), any(NoticeUpdateRequest.class))).thenReturn(noticeIdResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			put("/notice/{notice-id}", noticeId)
				.content(objectMapper.writeValueAsString(noticeUpdateRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("update-notice",
				pathParameters(parameterWithName("notice-id").description("수정할 공지사항 id")),
				requestFields(
					fieldWithPath("managerId").type(JsonFieldType.STRING).description("공지사항을 수정할 매니저의 아이디"),
					fieldWithPath("title").type(JsonFieldType.STRING).description("수정할 공지사항 제목"),
					fieldWithPath("content").type(JsonFieldType.STRING).description("수정할 공지사항 본문")
				),
				responseFields(
					fieldWithPath("noticeId").type(JsonFieldType.NUMBER).description("수정된 공지사항 아이디")
				)
			));
	}

	@Test
	@DisplayName("Delete Notice Controller Test - Success")
	void deleteNoticeSuccess() throws Exception {
		//given
		NoticeDeleteResponse noticeDeleteResponse = new NoticeDeleteResponse(noticeId, true);

		when(noticeService.deleteNotice(anyLong(), anyString())).thenReturn(noticeDeleteResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			delete("/notice/{notice-id}", noticeId)
				.header("managerId", managerId)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("delete-notice",
				pathParameters(parameterWithName("notice-id").description("삭제할 공지사 id")),
				responseFields(
					fieldWithPath("noticeId").type(JsonFieldType.NUMBER).description("삭제한 공지사항 아이디"),
					fieldWithPath("state").type(JsonFieldType.BOOLEAN).description("공지사항의 상태 false : 정상 true : 삭제됨")
				)
			));
	}
}