package com.encore.auction.controller.auction.api;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
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

import com.encore.auction.controller.auction.requests.AuctionCreateRequest;
import com.encore.auction.controller.auction.requests.AuctionUpdateRequest;
import com.encore.auction.controller.auction.responses.AuctionDeleteResponse;
import com.encore.auction.controller.auction.responses.AuctionDetailsResponse;
import com.encore.auction.controller.auction.responses.AuctionIdResponse;
import com.encore.auction.controller.auction.responses.AuctionRetrieveResponse;
import com.encore.auction.controller.comment.responses.CommentDetailsResponse;
import com.encore.auction.model.auction.item.ItemCategory;
import com.encore.auction.service.auction.AuctionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = AuctionController.class)
@AutoConfigureRestDocs
class AuctionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuctionService auctionService;

	@Autowired
	private ObjectMapper objectMapper;

	private final Long auctionItemId = 13L;

	private final String addressCode = "안녕";

	private final String managerId = "규리종현정일재현지영";

	private final String auctionItemName = "엔코아노트북";

	private final String location = "서울시 금천구";

	private final String lotNumber = "22";

	private final String addressDetail = "독산동";

	private final Long appraisedValue = 190000000L;

	private final LocalDateTime auctionStartDate = LocalDateTime.of(2019, 12, 25, 00, 00, 00, 0000);

	private final LocalDateTime auctionEndDate = LocalDateTime.of(2022, 12, 25, 00, 00, 00, 0000);

	private final ItemCategory itemCategory = ItemCategory.APARTMENT;

	private final Double areaSize = 123.4;

	private final Integer auctionFailedCount = 12;

	private final Integer hit = 34;

	@Test
	@DisplayName("Create Auction Item Controller Test - Success")
	void createAuctionSuccess() throws Exception {
		// given
		AuctionCreateRequest auctionCreateRequest = new AuctionCreateRequest(addressCode, managerId, auctionItemName,
			location, lotNumber, addressDetail, appraisedValue, auctionStartDate, auctionEndDate, itemCategory,
			areaSize);

		AuctionIdResponse auctionIdResponse = new AuctionIdResponse(auctionItemId);

		when(auctionService.createAuctionItem(any(AuctionCreateRequest.class))).thenReturn(auctionIdResponse);
		// when
		ResultActions resultActions = mockMvc.perform(
			post("/auction/create")
				.content(objectMapper.writeValueAsString(auctionCreateRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isCreated())
			.andDo(print())
			.andDo(document("auction-create",
				requestFields(
					fieldWithPath("managerId").type(JsonFieldType.STRING).description("매물 등록할 매니저 아이디"),
					fieldWithPath("addressCode").type(JsonFieldType.STRING).description("매물 주소 코드"),
					fieldWithPath("auctionItemName").type(JsonFieldType.STRING).description("매물 이름"),
					fieldWithPath("location").type(JsonFieldType.STRING).description("매물 위치"),
					fieldWithPath("lotNumber").type(JsonFieldType.STRING).description("매물 지번"),
					fieldWithPath("addressDetail").type(JsonFieldType.STRING).description("매물 상세 주소"),
					fieldWithPath("appraisedValue").type(JsonFieldType.NUMBER).description("감정가"),
					fieldWithPath("auctionStartDate").type(JsonFieldType.STRING).description("경매 시작일"),
					fieldWithPath("auctionEndDate").type(JsonFieldType.STRING).description("경매 종료일"),
					fieldWithPath("itemCategory").type(JsonFieldType.STRING).description("건물 종류"),
					fieldWithPath("areaSize").type(JsonFieldType.NUMBER).description("면적")
				),
				responseFields(
					fieldWithPath("auctionItemId").type(JsonFieldType.NUMBER).description("매물 번호")
				)
			));
	}

	@Test
	@DisplayName("Retrieve Auction Item Controller Test - Success")
	void retrieveAuctionSuccess() throws Exception {
		// given
		List<CommentDetailsResponse> commentDetailsResponseList = new ArrayList<>();
		commentDetailsResponseList.add(new CommentDetailsResponse("tester1", auctionItemId, "사고 싶네요"));

		AuctionRetrieveResponse auctionRetrieveResponse = new AuctionRetrieveResponse(auctionItemId, addressCode,
			managerId,
			auctionItemName,
			location, lotNumber, addressDetail, appraisedValue, auctionStartDate, auctionEndDate, itemCategory,
			areaSize, auctionFailedCount, hit, commentDetailsResponseList);

		when(auctionService.retrieveAuctionItem(anyLong())).thenReturn(auctionRetrieveResponse);
		// when
		ResultActions resultActions = mockMvc.perform(
			get("/auction/{auction-item-id}", auctionItemId)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("auction-retrieve",
				pathParameters(parameterWithName("auction-item-id").description("찾고 싶은 Auction Item Id")),
				responseFields(
					fieldWithPath("auctionItemId").type(JsonFieldType.NUMBER).description("매물 등록할 물건 번호"),
					fieldWithPath("managerId").type(JsonFieldType.STRING).description("매물 등록할 매니저 아이디"),
					fieldWithPath("addressCode").type(JsonFieldType.STRING).description("매물 주소 코드"),
					fieldWithPath("auctionItemName").type(JsonFieldType.STRING).description("매물 이름"),
					fieldWithPath("location").type(JsonFieldType.STRING).description("매물 위치"),
					fieldWithPath("lotNumber").type(JsonFieldType.STRING).description("매물 지번"),
					fieldWithPath("addressDetail").type(JsonFieldType.STRING).description("매물 상세 주소"),
					fieldWithPath("appraisedValue").type(JsonFieldType.NUMBER).description("감정가"),
					fieldWithPath("auctionStartDate").type(JsonFieldType.STRING).description("경매 시작일"),
					fieldWithPath("auctionEndDate").type(JsonFieldType.STRING).description("경매 종료일"),
					fieldWithPath("itemCategory").type(JsonFieldType.STRING).description("건물 종류"),
					fieldWithPath("areaSize").type(JsonFieldType.NUMBER).description("면적"),
					fieldWithPath("auctionFailedCount").type(JsonFieldType.NUMBER).description("유찰 횟수"),
					fieldWithPath("hit").type(JsonFieldType.NUMBER).description("조회수"),
					fieldWithPath("commentDetailsResponseList").type(JsonFieldType.ARRAY).description("조회수"),
					fieldWithPath("commentDetailsResponseList[].userId").type(JsonFieldType.STRING)
						.description("작성한 유저 아이디"),
					fieldWithPath("commentDetailsResponseList[].auctionItemId").type(JsonFieldType.NUMBER)
						.description("댓글이 작성돼있는 auction item 아이디"),
					fieldWithPath("commentDetailsResponseList[].content").type(JsonFieldType.STRING)
						.description("댓글 내용")
				)
			));
	}

	@Test
	@DisplayName("Update Auction Item Controller Test - Success")
	void updateAuctionSuccess() throws Exception {
		//given
		AuctionUpdateRequest auctionUpdateRequest = new AuctionUpdateRequest(auctionItemId, addressCode, managerId,
			auctionItemName,
			location, lotNumber, addressDetail, appraisedValue, auctionStartDate, auctionEndDate, itemCategory,
			areaSize, auctionFailedCount, hit);

		AuctionDetailsResponse auctionDetailsResponse = new AuctionDetailsResponse(auctionItemId, addressCode,
			managerId,
			auctionItemName,
			location, lotNumber, addressDetail, appraisedValue, auctionStartDate, auctionEndDate, itemCategory,
			areaSize, auctionFailedCount, hit);

		when(auctionService.updateAuctionItem(anyLong(), any(AuctionUpdateRequest.class))).thenReturn(
			auctionDetailsResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			put("/auction/{auction-item-id}", auctionItemId)
				.content(objectMapper.writeValueAsString(auctionUpdateRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("auction-update",
				pathParameters(parameterWithName("auction-item-id").description("수정할 Auction Item Id")),
				requestFields(
					fieldWithPath("auctionItemId").type(JsonFieldType.NUMBER).description("매물 등록할 물건 번호"),
					fieldWithPath("managerId").type(JsonFieldType.STRING).description("매물 등록할 매니저 아이디"),
					fieldWithPath("addressCode").type(JsonFieldType.STRING).description("매물 주소 코드"),
					fieldWithPath("auctionItemName").type(JsonFieldType.STRING).description("매물 이름"),
					fieldWithPath("location").type(JsonFieldType.STRING).description("매물 위치"),
					fieldWithPath("lotNumber").type(JsonFieldType.STRING).description("매물 지번"),
					fieldWithPath("addressDetail").type(JsonFieldType.STRING).description("매물 상세 주소"),
					fieldWithPath("appraisedValue").type(JsonFieldType.NUMBER).description("감정가"),
					fieldWithPath("auctionStartDate").type(JsonFieldType.STRING).description("경매 시작일"),
					fieldWithPath("auctionEndDate").type(JsonFieldType.STRING).description("경매 종료일"),
					fieldWithPath("itemCategory").type(JsonFieldType.STRING).description("건물 종류"),
					fieldWithPath("areaSize").type(JsonFieldType.NUMBER).description("면적"),
					fieldWithPath("auctionFailedCount").type(JsonFieldType.NUMBER).description("유찰 횟수"),
					fieldWithPath("hit").type(JsonFieldType.NUMBER).description("조회수")
				),
				responseFields(
					fieldWithPath("auctionItemId").type(JsonFieldType.NUMBER).description("매물 등록할 물건 번호"),
					fieldWithPath("managerId").type(JsonFieldType.STRING).description("매물 등록할 매니저 아이디"),
					fieldWithPath("addressCode").type(JsonFieldType.STRING).description("매물 주소 코드"),
					fieldWithPath("auctionItemName").type(JsonFieldType.STRING).description("매물 이름"),
					fieldWithPath("location").type(JsonFieldType.STRING).description("매물 위치"),
					fieldWithPath("lotNumber").type(JsonFieldType.STRING).description("매물 지번"),
					fieldWithPath("addressDetail").type(JsonFieldType.STRING).description("매물 상세 주소"),
					fieldWithPath("appraisedValue").type(JsonFieldType.NUMBER).description("감정가"),
					fieldWithPath("auctionStartDate").type(JsonFieldType.STRING).description("경매 시작일"),
					fieldWithPath("auctionEndDate").type(JsonFieldType.STRING).description("경매 종료일"),
					fieldWithPath("itemCategory").type(JsonFieldType.STRING).description("건물 종류"),
					fieldWithPath("areaSize").type(JsonFieldType.NUMBER).description("면적"),
					fieldWithPath("auctionFailedCount").type(JsonFieldType.NUMBER).description("유찰 횟수"),
					fieldWithPath("hit").type(JsonFieldType.NUMBER).description("조회수")
				)
			));
	}

	@Test
	@DisplayName("delete Auction Item Controller Test - Success")
	void deleteAuctionSuccess() throws Exception {
		//given
		AuctionDeleteResponse auctionDeleteResponse = new AuctionDeleteResponse(auctionItemId, true);

		when(auctionService.deleteAuctionItem(anyLong(), anyString())).thenReturn(auctionDeleteResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			delete("/auction/{auction-item-id}", auctionItemId)
				.header("managerId", managerId)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("auction-delete",
				pathParameters(parameterWithName("auction-item-id").description("삭제할 Auction Item Id")),
				responseFields(
					fieldWithPath("auctionItemId").type(JsonFieldType.NUMBER).description("삭제한 매물"),
					fieldWithPath("state").type(JsonFieldType.BOOLEAN).description("매니저 상태")
				)
			));
	}

}
