package com.encore.auction.controller.bidding.bidding.api;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

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

import com.encore.auction.controller.bidding.bidding.requests.BiddingRegisterRequest;
import com.encore.auction.controller.bidding.bidding.requests.BiddingUpdateRequest;
import com.encore.auction.controller.bidding.bidding.responses.BiddingDeleteResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingIdResponse;
import com.encore.auction.controller.bidding.bidding.responses.BiddingRetrieveResponse;
import com.encore.auction.model.bidding.aftbidding.BiddingResult;
import com.encore.auction.service.bidding.bidding.BiddingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = BiddingController.class)
@AutoConfigureRestDocs
class BiddingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BiddingService biddingService;

	@Autowired
	private ObjectMapper objectMapper;

	private final Long biddingId = 13L;

	private final String userId = "tester1";
	private final Long auctionItemId = 3L;
	private final Long amount = 160000000L;

	private final String auctionItemName = "강남 정정일아파트 1004동 1004호";
	private final LocalDateTime biddingDate = LocalDateTime.parse("2022-12-10T13:12:31");
	private final LocalDateTime decideDate = null;
	private final BiddingResult biddingResult = null;

	@Test
	@DisplayName("Register Bidding Controller Test - Success")
	void registerBiddingSuccess() throws Exception {
		//given
		BiddingRegisterRequest biddingRegisterRequest = new BiddingRegisterRequest(userId, auctionItemId, amount);
		BiddingIdResponse biddingIdResponse = new BiddingIdResponse(biddingId);

		when(biddingService.registerBidding(any(BiddingRegisterRequest.class))).thenReturn(biddingIdResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			post("/bidding")
				.content(objectMapper.writeValueAsString(biddingRegisterRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isCreated())
			.andDo(print())
			.andDo(document("bidding-register",
				requestFields(
					fieldWithPath("userId").type(JsonFieldType.STRING).description("입찰을 요청할 User Id"),
					fieldWithPath("auctionItemId").type(JsonFieldType.NUMBER).description("입찰 하고자 하는 auction item 아이디"),
					fieldWithPath("amount").type(JsonFieldType.NUMBER).description("입찰 참여할 금액")
				),
				responseFields(
					fieldWithPath("biddingId").type(JsonFieldType.NUMBER).description("생성된 입찰 Id")
				)
			));
	}

	@Test
	@DisplayName("Retrieve Bidding Controller Test - Success")
	void retrieveBiddingSuccess() throws Exception {
		//given
		BiddingRetrieveResponse biddingRetrieveResponse = new BiddingRetrieveResponse(biddingId, auctionItemId,
			auctionItemName, biddingDate, amount, decideDate, biddingResult);

		when(biddingService.retrieveBidding(anyLong())).thenReturn(biddingRetrieveResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			get("/bidding/{bidding-id}", biddingId)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("retrieve-bidding",
				pathParameters(parameterWithName("bidding-id").description("찾고 싶은 입찰 id")),
				responseFields(
					fieldWithPath("biddingId").type(JsonFieldType.NUMBER).description("입찰 아이디"),
					fieldWithPath("auctionItemId").type(JsonFieldType.NUMBER).description("입찰한 auction item 아이디"),
					fieldWithPath("auctionItemName").type(JsonFieldType.STRING).description("입찰한 auction item 제목"),
					fieldWithPath("biddingDate").type(JsonFieldType.STRING).description("입찰한 시간 날짜"),
					fieldWithPath("amount").type(JsonFieldType.NUMBER).description("입찰한 금액"),
					fieldWithPath("decideDate").type(JsonFieldType.STRING).description("입찰 결과 결정 날짜 시간"),
					fieldWithPath("biddingResult").type(JsonFieldType.STRING).description("입찰 결과")
				)
			));
	}

	@Test
	@DisplayName("Update Bidding Controller Test - Success")
	void updateBiddingSuccess() throws Exception {
		//given
		BiddingUpdateRequest biddingUpdateRequest = new BiddingUpdateRequest(userId, amount);
		BiddingDetailsResponse biddingDetailsResponse = new BiddingDetailsResponse(biddingId, auctionItemId,
			auctionItemName, biddingDate, amount);

		when(biddingService.updateBidding(anyLong(), any(BiddingUpdateRequest.class))).thenReturn(
			biddingDetailsResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			put("/bidding/{bidding-id}", biddingId)
				.content(objectMapper.writeValueAsString(biddingUpdateRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("update-bidding",
				pathParameters(parameterWithName("bidding-id").description("수정할 bidding id")),
				requestFields(
					fieldWithPath("userId").type(JsonFieldType.STRING).description("입찰한 user 아이디"),
					fieldWithPath("amount").type(JsonFieldType.NUMBER).description("수정할 입찰 금액")
				),
				responseFields(
					fieldWithPath("biddingId").type(JsonFieldType.NUMBER).description("입찰 아이디"),
					fieldWithPath("auctionItemId").type(JsonFieldType.NUMBER).description("입찰한 auction item 아이디"),
					fieldWithPath("auctionItemName").type(JsonFieldType.STRING).description("입찰한 auction item 제목"),
					fieldWithPath("biddingDate").type(JsonFieldType.STRING).description("입찰한 시간 날짜"),
					fieldWithPath("amount").type(JsonFieldType.NUMBER).description("입찰한 금액")
				)
			));
	}

	@Test
	@DisplayName("Delete Bidding Controller Test - Success")
	void deleteBiddingSuccess() throws Exception {
		//given
		BiddingDeleteResponse biddingDeleteResponse = new BiddingDeleteResponse(biddingId, true);

		when(biddingService.deleteBidding(anyLong(), anyString())).thenReturn(biddingDeleteResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			delete("/bidding/{bidding-id}", biddingId)
				.header("userId", userId)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("delete-bidding",
				pathParameters(parameterWithName("bidding-id").description("수정할 bidding id")),
				responseFields(
					fieldWithPath("biddingId").type(JsonFieldType.NUMBER).description("입찰 아이디"),
					fieldWithPath("state").type(JsonFieldType.BOOLEAN).description("입찰의 상태 false : 정상 true : 삭제됨")
				)
			));
	}
}