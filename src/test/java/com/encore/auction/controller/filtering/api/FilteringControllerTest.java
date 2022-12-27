// package com.encore.auction.controller.filtering.api;
//
// import static org.mockito.Mockito.*;
// import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
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
// import com.encore.auction.controller.filtering.responses.FilteringItemsListResponse;
// import com.encore.auction.controller.filtering.responses.FilteringItemsResponse;
// import com.encore.auction.model.auction.item.ItemCategory;
// import com.encore.auction.service.filtering.FilteringService;
// import com.fasterxml.jackson.databind.ObjectMapper;
//
// @WebMvcTest(controllers = FilteringController.class)
// @AutoConfigureRestDocs
// class FilteringControllerTest {
//
// 	@Autowired
// 	private MockMvc mockMvc;
//
// 	@MockBean
// 	private FilteringService filteringService;
//
// 	@Autowired
// 	private ObjectMapper objectMapper;
//
// 	private Long id = Long.valueOf(0);
//
// 	private String managerId = "tester1";
//
// 	private String addressCode = "10312";
//
// 	private String auctionItemName = "tester1";
//
// 	private String location = "Banghak-dong";
//
// 	private String lotNumber = "1234";
//
// 	private String addressDetail = "1234";
//
// 	private Long appraisedValue = Long.valueOf(1234);
//
// 	private LocalDateTime auctionStartDate = LocalDateTime.parse("2021-01-01T00:00");
//
// 	private LocalDateTime auctionEndDate = LocalDateTime.parse("2021-01-02T00:00");
//
// 	private ItemCategory itemCategory = ItemCategory.valueOf("APARTMENT");
//
// 	private Double areaSize = 123.45;
//
// 	private Integer auctionFailedCount = 1;
//
// 	private Integer hit = 1;
//
// 	private Boolean state = true;
//
// 	@Test
// 	@DisplayName("Getting AuctionItemList Controller Test - Success")
// 	void getAuctionItemListSusccess() throws Exception {
// 		// given
// 		List<FilteringItemsResponse> filteringItems = new ArrayList<FilteringItemsResponse>();
// 		FilteringItemsResponse filteringItemsResponse1 = new FilteringItemsResponse(id, managerId, addressCode,
// 			auctionItemName, location, lotNumber, addressDetail, appraisedValue, auctionStartDate,
// 			auctionEndDate, itemCategory, areaSize, auctionFailedCount, hit, state);
// 		FilteringItemsResponse filteringItemsResponse2 = new FilteringItemsResponse(id, managerId, addressCode,
// 			auctionItemName, location, lotNumber, addressDetail, appraisedValue, auctionStartDate,
// 			auctionEndDate, itemCategory, areaSize, auctionFailedCount, hit, state);
// 		filteringItems.add(filteringItemsResponse1);
// 		filteringItems.add(filteringItemsResponse2);
// 		FilteringItemsListResponse filteringItemsListResponse = new FilteringItemsListResponse(filteringItems);
//
// 		when(filteringService.getAuctionItemList()).thenReturn(filteringItemsListResponse);
// 		// when
// 		ResultActions resultActions = mockMvc.perform(
// 			get("/auction/get/list")
// 				.accept(MediaType.APPLICATION_JSON));
// 		// then
// 		resultActions.andExpect(status().isOk())
// 			.andDo(print())
// 			.andDo(document("retrieve-auction-items",
// 				responseFields(
// 					List.of(
// 						fieldWithPath("filteringItemsResponseList").type(JsonFieldType.ARRAY).description("결과 데이터"),
// 						fieldWithPath("filteringItemsResponseList[].auctionItemId").type(JsonFieldType.NUMBER)
// 							.description("매물 아이디"),
// 						fieldWithPath("filteringItemsResponseList[].managerId").type(JsonFieldType.STRING)
// 							.description("매물 처리 담당 매니저"),
// 						fieldWithPath("filteringItemsResponseList[].addressCode").type(JsonFieldType.STRING)
// 							.description("매물의 주소"),
// 						fieldWithPath("filteringItemsResponseList[].auctionItemName").type(JsonFieldType.STRING)
// 							.description("매물 이름"),
// 						fieldWithPath("filteringItemsResponseList[].location").type(JsonFieldType.STRING)
// 							.description("매물 위치"),
// 						fieldWithPath("filteringItemsResponseList[].lotNumber").type(JsonFieldType.STRING)
// 							.description("매물 번호"),
// 						fieldWithPath("filteringItemsResponseList[].addressDetail").type(JsonFieldType.STRING)
// 							.description("매물 상세주소"),
// 						fieldWithPath("filteringItemsResponseList[].appraisedValue").type(JsonFieldType.NUMBER)
// 							.description("매물 감정가"),
// 						fieldWithPath("filteringItemsResponseList[].auctionStartDate").type(JsonFieldType.STRING)
// 							.description("경매 시작 날짜"),
// 						fieldWithPath("filteringItemsResponseList[].auctionEndDate").type(JsonFieldType.STRING)
// 							.description("경매 종료 날짜"),
// 						fieldWithPath("filteringItemsResponseList[].itemCategory").type(JsonFieldType.STRING)
// 							.description("매물 종류"),
// 						fieldWithPath("filteringItemsResponseList[].areaSize").type(JsonFieldType.NUMBER)
// 							.description("매물 면적"),
// 						fieldWithPath("filteringItemsResponseList[].auctionFailedCount").type(JsonFieldType.NUMBER)
// 							.description("유찰 횟수"),
// 						fieldWithPath("filteringItemsResponseList[].hit").type(JsonFieldType.NUMBER)
// 							.description("매물 조회수"),
// 						fieldWithPath("filteringItemsResponseList[].state").type(JsonFieldType.BOOLEAN)
// 							.description("매물 입찰 가능 여부")
// 					)
// 				)
// 			));
// 	}
//
// 	@Test
// 	@DisplayName("Getting AuctionItemList Filtered By Date Controller Test - Success")
// 	void getFilteredAuctionItemListSuccess() throws Exception {
// 		// given
// 		List<FilteringItemsResponse> filteringItems = new ArrayList<FilteringItemsResponse>();
// 		FilteringItemsResponse filteringItemsResponse1 = new FilteringItemsResponse(id, managerId, addressCode,
// 			auctionItemName, location, lotNumber, addressDetail, appraisedValue, auctionStartDate,
// 			auctionEndDate, itemCategory, areaSize, auctionFailedCount, hit, state);
// 		FilteringItemsResponse filteringItemsResponse2 = new FilteringItemsResponse(id, managerId, addressCode,
// 			auctionItemName, location, lotNumber, addressDetail, appraisedValue, auctionStartDate,
// 			auctionEndDate, itemCategory, areaSize, auctionFailedCount, hit, state);
// 		filteringItems.add(filteringItemsResponse1);
// 		filteringItems.add(filteringItemsResponse2);
// 		FilteringItemsListResponse filteringItemsListResponse = new FilteringItemsListResponse(filteringItems);
//
// 		when(filteringService.getFilteredAuctionItemList(anyString(), anyString(), anyString(), anyInt()))
// 			.thenReturn(filteringItemsListResponse);
// 		// when
// 		ResultActions resultActions = mockMvc.perform(
// 			get("/auction/get/list/{address}/{date}/{category}/{auction-failed-count}", addressCode, auctionEndDate,
// 				itemCategory, auctionFailedCount)
// 				.accept(MediaType.APPLICATION_JSON));
// 		// then
// 		resultActions.andExpect(status().isOk())
// 			.andDo(print())
// 			.andDo(document("retrieve-auction-items",
// 				pathParameters(
// 					parameterWithName("address").description("원하는 지역"),
// 					parameterWithName("date").description("원하는 날짜"),
// 					parameterWithName("category").description("원하는 매물 종류"),
// 					parameterWithName("auction-failed-count").description("최대 유찰 횟수")
// 				),
// 				responseFields(
// 					List.of(
// 						fieldWithPath("filteringItemsResponseList").type(JsonFieldType.ARRAY).description("결과 데이터"),
// 						fieldWithPath("filteringItemsResponseList[].auctionItemId").type(JsonFieldType.NUMBER)
// 							.description("매물 아이디"),
// 						fieldWithPath("filteringItemsResponseList[].managerId").type(JsonFieldType.STRING)
// 							.description("매물 처리 담당 매니저"),
// 						fieldWithPath("filteringItemsResponseList[].addressCode").type(JsonFieldType.STRING)
// 							.description("매물의 주소"),
// 						fieldWithPath("filteringItemsResponseList[].auctionItemName").type(JsonFieldType.STRING)
// 							.description("매물 이름"),
// 						fieldWithPath("filteringItemsResponseList[].location").type(JsonFieldType.STRING)
// 							.description("매물 위치"),
// 						fieldWithPath("filteringItemsResponseList[].lotNumber").type(JsonFieldType.STRING)
// 							.description("매물 번호"),
// 						fieldWithPath("filteringItemsResponseList[].addressDetail").type(JsonFieldType.STRING)
// 							.description("매물 상세주소"),
// 						fieldWithPath("filteringItemsResponseList[].appraisedValue").type(JsonFieldType.NUMBER)
// 							.description("매물 감정가"),
// 						fieldWithPath("filteringItemsResponseList[].auctionStartDate").type(JsonFieldType.STRING)
// 							.description("경매 시작 날짜"),
// 						fieldWithPath("filteringItemsResponseList[].auctionEndDate").type(JsonFieldType.STRING)
// 							.description("경매 종료 날짜"),
// 						fieldWithPath("filteringItemsResponseList[].itemCategory").type(JsonFieldType.STRING)
// 							.description("매물 종류"),
// 						fieldWithPath("filteringItemsResponseList[].areaSize").type(JsonFieldType.NUMBER)
// 							.description("매물 면적"),
// 						fieldWithPath("filteringItemsResponseList[].auctionFailedCount").type(JsonFieldType.NUMBER)
// 							.description("유찰 횟수"),
// 						fieldWithPath("filteringItemsResponseList[].hit").type(JsonFieldType.NUMBER)
// 							.description("매물 조회수"),
// 						fieldWithPath("filteringItemsResponseList[].state").type(JsonFieldType.BOOLEAN)
// 							.description("매물 입찰 가능 여부")
// 					)
// 				)
// 			));
// 	}
// }