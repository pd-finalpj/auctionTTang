// package com.encore.auction.controller.bidding.bidding.api;
//
// import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
// import static org.mockito.Mockito.*;
// import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
// import static org.springframework.restdocs.payload.PayloadDocumentation.*;
// import static org.springframework.restdocs.request.RequestDocumentation.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.restdocs.payload.JsonFieldType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.ResultActions;
//
// import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsListResponse;
// import com.encore.auction.controller.bidding.bidding.responses.BiddingDetailsResponse;
// import com.encore.auction.service.bidding.bidding.BiddingListService;
//
// @WebMvcTest(controllers = BiddingListController.class)
// @AutoConfigureRestDocs
// class BiddingListControllerTest {
//
// 	@Autowired
// 	private MockMvc mockMvc;
//
// 	@MockBean
// 	private BiddingListService biddingListService;
//
// 	private final String userId = "tester1";
//
// 	private final Long biddingId = 1L;
// 	private final Long auctionItemId = 3L;
// 	private final String auctionItemName = "강남 정정일아파트 1004동 1004호";
// 	private final LocalDateTime biddingDate = LocalDateTime.parse("2022-12-10T13:12:31");
// 	private final Long amount = 136000000L;
//
// 	@Test
// 	@DisplayName("Retrieve Bidding List By UserId - Success")
// 	void retrieveBiddingListByUserIdSuccess() throws Exception {
// 		//given
// 		List<BiddingDetailsResponse> biddingDetailsResponseList = new ArrayList<>();
// 		for (int i = 0; i < 5; i++) {
// 			biddingDetailsResponseList.add(
// 				new BiddingDetailsResponse(biddingId + i, auctionItemId + i, auctionItemName + i, biddingDate,
// 					amount + i));
// 		}
// 		BiddingDetailsListResponse biddingDetailsListResponse = new BiddingDetailsListResponse(userId,
// 			biddingDetailsResponseList);
//
// 		when(biddingListService.retrieveBiddingListByUserId(anyString())).thenReturn(biddingDetailsListResponse);
// 		//when
// 		ResultActions resultActions = mockMvc.perform(
// 			get("/bidding-list/{user-id}", userId)
// 				.accept(MediaType.APPLICATION_JSON));
// 		//then
// 		resultActions.andExpect(status().isOk())
// 			.andDo(print())
// 			.andDo(document("retrieve-bidding-list",
// 				pathParameters(parameterWithName("user-id").description("입찰 참여 리스트를 찾고 싶은 user id")),
// 				responseFields(
// 					fieldWithPath("userId").type(JsonFieldType.STRING).description("유저 아이디"),
// 					fieldWithPath("biddingDetailsResponseList").type(JsonFieldType.ARRAY).description("유저의 입찰 참여 리스트"),
// 					fieldWithPath("biddingDetailsResponseList[].biddingId").type(JsonFieldType.NUMBER)
// 						.description("입찰 id"),
// 					fieldWithPath("biddingDetailsResponseList[].auctionItemId").type(JsonFieldType.NUMBER)
// 						.description("입찰한 auction item id"),
// 					fieldWithPath("biddingDetailsResponseList[].auctionItemName").type(JsonFieldType.STRING)
// 						.description("입찰한 auction item의 제목"),
// 					fieldWithPath("biddingDetailsResponseList[].biddingDate").type(JsonFieldType.STRING)
// 						.description("입찰한 날짜 및 시간"),
// 					fieldWithPath("biddingDetailsResponseList[].amount").type(JsonFieldType.NUMBER)
// 						.description("입찰한 금액")
// 				)
// 			));
// 	}
// }