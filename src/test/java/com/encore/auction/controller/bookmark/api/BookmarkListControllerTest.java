package com.encore.auction.controller.bookmark.api;

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

import com.encore.auction.controller.bookmark.responses.BookmarkDetailsListResponse;
import com.encore.auction.controller.bookmark.responses.BookmarkDetailsResponse;
import com.encore.auction.model.auction.item.ItemCategory;
import com.encore.auction.service.bookmark.BookmarkListService;

@WebMvcTest(controllers = BookmarkListController.class)
@AutoConfigureRestDocs
class BookmarkListControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookmarkListService bookmarkListService;

	private final String userId = "tester1";

	private final Long auctionItemId = 1L;
	private final String auctionAddressCode = "05320";
	private final String auctionItemName = "강남 정정일아파트 1004동 1004호";
	private final String auctionLocation = "압구정동";
	private final String auctionLotNumber = "1004-14";
	private final String addressDetail = "1004동 1004호";
	private final Long appraisedValue = 130000000L;
	private final LocalDateTime auctionStartDate = LocalDateTime.parse("2022-12-13T13:00:00");
	private final LocalDateTime auctionEndDate = LocalDateTime.parse("2022-12-24T16:00:00");
	private final ItemCategory itemCategory = ItemCategory.APARTMENT;
	private final Double areaSize = 198.347107;
	private final Integer auctionFailedCount = 3;
	private final Integer hit = 137;

	@Test
	@DisplayName("Retrieve Bookmark List By UserId - Success")
	void retrieveBookmarkListSuccess() throws Exception {
		//given
		List<BookmarkDetailsResponse> bookmarkDetailsResponseList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			bookmarkDetailsResponseList.add(
				new BookmarkDetailsResponse(auctionItemId + i, auctionAddressCode, auctionItemName + i,
					auctionLocation,
					auctionLotNumber + i, addressDetail + i, appraisedValue + i, auctionStartDate, auctionEndDate,
					itemCategory, areaSize + i, auctionFailedCount + i, hit + i));
		}

		BookmarkDetailsListResponse bookmarkDetailsListResponse = new BookmarkDetailsListResponse(userId,
			bookmarkDetailsResponseList);

		when(bookmarkListService.retrieveBookmarkListByUserId(anyString())).thenReturn(bookmarkDetailsListResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			get("/bookmark-list/{user-id}", userId)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("retrieve-bookmark-list",
				pathParameters(parameterWithName("user-id").description("북마크 리스트를 찾고 싶은 user id")),
				responseFields(
					fieldWithPath("userId").type(JsonFieldType.STRING).description("유저 아이디"),
					fieldWithPath("bookmarkDetailsResponseList").type(JsonFieldType.ARRAY)
						.description("북마크 auction 리스트"),
					fieldWithPath("bookmarkDetailsResponseList[].auctionItemId").type(JsonFieldType.NUMBER)
						.description("북마크 된 auction item id"),
					fieldWithPath("bookmarkDetailsResponseList[].auctionAddressCode").type(JsonFieldType.STRING)
						.description("북마크 된 auction 의 주소 코드"),
					fieldWithPath("bookmarkDetailsResponseList[].auctionItemName").type(JsonFieldType.STRING)
						.description("북마크 된 auction의 제목"),
					fieldWithPath("bookmarkDetailsResponseList[].auctionLocation").type(JsonFieldType.STRING)
						.description("북마크 된 auction의 동 주소"),
					fieldWithPath("bookmarkDetailsResponseList[].auctionLotNumber").type(JsonFieldType.STRING)
						.description("북마크 된 auction의 지번"),
					fieldWithPath("bookmarkDetailsResponseList[].addressDetail").type(JsonFieldType.STRING)
						.description("북마크 된 auction의 주소 디테일"),
					fieldWithPath("bookmarkDetailsResponseList[].appraisedValue").type(JsonFieldType.NUMBER)
						.description("북마크 된 auction의 감정가"),
					fieldWithPath("bookmarkDetailsResponseList[].auctionStartDate").type(JsonFieldType.STRING)
						.description("북마크 된 auction의 입찰 시작 date"),
					fieldWithPath("bookmarkDetailsResponseList[].auctionEndDate").type(JsonFieldType.STRING)
						.description("북마크 된 auction의 입찰 종료 date"),
					fieldWithPath("bookmarkDetailsResponseList[].itemCategory").type(JsonFieldType.STRING)
						.description("북마크 된 auction의 건물 종류"),
					fieldWithPath("bookmarkDetailsResponseList[].areaSize").type(JsonFieldType.NUMBER)
						.description("북마크 된 auction의 면적 m단위"),
					fieldWithPath("bookmarkDetailsResponseList[].auctionFailedCount").type(JsonFieldType.NUMBER)
						.description("북마크 된 auction의 유찰 횟수"),
					fieldWithPath("bookmarkDetailsResponseList[].hit").type(JsonFieldType.NUMBER)
						.description("북마크 된 auction의 조회수")
				)
			));
	}
}