package com.encore.auction.controller.bidding.aftbidding.api;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

import com.encore.auction.controller.bidding.aftbidding.responses.AftBiddingDetailsListResponse;
import com.encore.auction.controller.bidding.aftbidding.responses.AftBiddingDetailsResponse;
import com.encore.auction.model.bidding.aftbidding.BiddingResult;
import com.encore.auction.service.bidding.aftbidding.AftBiddingListService;

@WebMvcTest(controllers = AftBiddingListController.class)
@AutoConfigureRestDocs
class AftBiddingListControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AftBiddingListService aftBiddingListService;

	private final String userId = "tester1";

	private final Long aftBiddingId = 4L;
	private final Long biddingId = 1L;
	private final Long auctionItemId = 3L;
	private final String auctionItemName = "강남 정정일아파트 1004동 1004호";
	private final LocalDateTime biddingDate = LocalDateTime.parse("2022-12-10T13:12:31");
	private final Long amount = 136000000L;
	private final LocalDateTime decideDate = LocalDateTime.parse("2022-12-13T13:12:31");
	private final BiddingResult biddingResult = BiddingResult.SUCCESS;

	@Test
	@DisplayName("Retrieve Aft Bidding List By UserId - Success")
	void retrieveAftBiddingListByUserIdSuccess() throws Exception {
		//given
		List<AftBiddingDetailsResponse> aftBiddingDetailsResponseList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			aftBiddingDetailsResponseList.add(
				new AftBiddingDetailsResponse(aftBiddingId + i, biddingId + i, auctionItemId + i, auctionItemName + i,
					biddingDate,
					amount + i, decideDate, biddingResult));
		}
		AftBiddingDetailsListResponse aftBiddingDetailsListResponse = new AftBiddingDetailsListResponse(userId,
			aftBiddingDetailsResponseList);

		when(aftBiddingListService.retrieveAftBiddingListByUserId(anyString())).thenReturn(
			aftBiddingDetailsListResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			get("/aft-bidding-list/{user-id}", userId)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("retrieve-aft-bidding-list",
				pathParameters(parameterWithName("user-id").description("입찰 결과 리스트를 찾고 싶은 user id")),
				responseFields(
					fieldWithPath("userId").type(JsonFieldType.STRING).description("유저 아이디"),
					fieldWithPath("aftBiddingDetailsResponseList").type(JsonFieldType.ARRAY)
						.description("유저의 입찰 결과 리스트"),
					fieldWithPath("aftBiddingDetailsResponseList[].aftBiddingId").type(JsonFieldType.NUMBER)
						.description("입찰 결과 id"),
					fieldWithPath("aftBiddingDetailsResponseList[].biddingId").type(JsonFieldType.NUMBER)
						.description("입찰 id"),
					fieldWithPath("aftBiddingDetailsResponseList[].auctionItemId").type(JsonFieldType.NUMBER)
						.description("입찰한 auction item id"),
					fieldWithPath("aftBiddingDetailsResponseList[].auctionItemName").type(JsonFieldType.STRING)
						.description("입찰한 auction item의 제목"),
					fieldWithPath("aftBiddingDetailsResponseList[].biddingDate").type(JsonFieldType.STRING)
						.description("입찰한 날짜 및 시간"),
					fieldWithPath("aftBiddingDetailsResponseList[].amount").type(JsonFieldType.NUMBER)
						.description("입찰한 금액"),
					fieldWithPath("aftBiddingDetailsResponseList[].decideDate").type(JsonFieldType.STRING)
						.description("결과 결정 날짜 및 시간"),
					fieldWithPath("aftBiddingDetailsResponseList[].biddingResult").type(JsonFieldType.STRING)
						.description("입찰 결과")
				)
			));
	}
}