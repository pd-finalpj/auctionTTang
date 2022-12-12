// package com.encore.auction.controller.comment.api;
//
// import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
// import static org.mockito.Mockito.*;
// import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
// import static org.springframework.restdocs.payload.PayloadDocumentation.*;
// import static org.springframework.restdocs.request.RequestDocumentation.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
// import com.encore.auction.controller.comment.requests.CommentRegisterRequest;
// import com.encore.auction.controller.comment.requests.CommentUpdateRequest;
// import com.encore.auction.controller.comment.responses.CommentDeleteResponse;
// import com.encore.auction.controller.comment.responses.CommentDetailsResponse;
// import com.encore.auction.controller.comment.responses.CommentIdResponse;
// import com.encore.auction.service.comment.CommentService;
// import com.fasterxml.jackson.databind.ObjectMapper;
//
// @WebMvcTest(controllers = CommentController.class)
// @AutoConfigureRestDocs
// class CommentControllerTest {
//
// 	@Autowired
// 	private MockMvc mockMvc;
//
// 	@MockBean
// 	private CommentService commentService;
//
// 	@Autowired
// 	private ObjectMapper objectMapper;
//
// 	private final String userId = "tester1";
// 	private final Long auctionItemId = 3L;
// 	private final String content = "돈만 있으면 사고 싶다";
// 	private final Long commentId = 13L;
//
// 	@Test
// 	@DisplayName("Register Comment Controller Test - Success")
// 	void registerCommentSuccess() throws Exception {
// 		//given
// 		CommentRegisterRequest commentRegisterRequest = new CommentRegisterRequest(userId, auctionItemId, content);
// 		CommentIdResponse commentIdResponse = new CommentIdResponse(commentId);
//
// 		when(commentService.registerComment(any(CommentRegisterRequest.class))).thenReturn(commentIdResponse);
// 		//when
// 		ResultActions resultActions = mockMvc.perform(
// 			post("/comment")
// 				.content(objectMapper.writeValueAsString(commentRegisterRequest))
// 				.contentType(MediaType.APPLICATION_JSON)
// 				.accept(MediaType.APPLICATION_JSON));
// 		//then
// 		resultActions.andExpect(status().isCreated())
// 			.andDo(
// 				document("comment-registration",
// 					requestFields(
// 						fieldWithPath("userId").type(JsonFieldType.STRING).description("댓글을 등록하고자 하는 user의 아이디"),
// 						fieldWithPath("auctionItemId").type(JsonFieldType.NUMBER)
// 							.description("댓글을 달고자 하는 auction item의 아이디"),
// 						fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용")
// 					),
// 					responseFields(
// 						fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("등록한 comment 아이디")
// 					))).andDo(print());
// 	}
//
// 	@Test
// 	@DisplayName("Retrieve Comment Controller Test - Success")
// 	void retrieveCommentSuccess() throws Exception {
// 		//given
// 		CommentDetailsResponse commentDetailsResponse = new CommentDetailsResponse(userId, auctionItemId, content);
//
// 		when(commentService.retrieveComment(anyLong())).thenReturn(commentDetailsResponse);
// 		//when
// 		ResultActions resultActions = mockMvc.perform(
// 			get("/comment/{comment-id}", commentId)
// 				.accept(MediaType.APPLICATION_JSON));
// 		//then
// 		resultActions.andExpect(status().isOk())
// 			.andDo(print())
// 			.andDo(document("retrieve-comment",
// 				pathParameters(parameterWithName("comment-id").description("찾고 싶은 댓글 id")),
// 				responseFields(
// 					fieldWithPath("userId").type(JsonFieldType.STRING).description("작성한 유저 아이디"),
// 					fieldWithPath("auctionItemId").type(JsonFieldType.NUMBER).description("댓글이 작성돼있는 auction item 아이디"),
// 					fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용")
// 				)
// 			));
// 	}
//
// 	@Test
// 	@DisplayName("Update Comment Controller Test - Success")
// 	void updateCommentSuccess() throws Exception {
// 		//given
// 		CommentUpdateRequest commentUpdateRequest = new CommentUpdateRequest(userId, auctionItemId, content);
// 		CommentIdResponse commentIdResponse = new CommentIdResponse(commentId);
//
// 		when(commentService.updateComment(anyLong(), any(CommentUpdateRequest.class))).thenReturn(commentIdResponse);
// 		//when
// 		ResultActions resultActions = mockMvc.perform(
// 			put("/comment/{comment-id}", commentId)
// 				.content(objectMapper.writeValueAsString(commentUpdateRequest))
// 				.contentType(MediaType.APPLICATION_JSON)
// 				.accept(MediaType.APPLICATION_JSON));
// 		//then
// 		resultActions.andExpect(status().isOk())
// 			.andDo(print())
// 			.andDo(document("update-comment",
// 				pathParameters(parameterWithName("comment-id").description("수정할 댓글 id")),
// 				requestFields(
// 					fieldWithPath("userId").type(JsonFieldType.STRING).description("수정할 댓글을 작성한 user 아이디"),
// 					fieldWithPath("auctionItemId").type(JsonFieldType.NUMBER)
// 						.description("수정할 댓글이 작성된 auction item 아이디"),
// 					fieldWithPath("content").type(JsonFieldType.STRING).description("수정할 댓글 본문")
// 				),
// 				responseFields(
// 					fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("등록한 comment 아이디")
// 				)
// 			));
// 	}
//
// 	@Test
// 	@DisplayName("Delete Comment Controller Test - Success")
// 	void deleteCommentSuccess() throws Exception {
// 		//given
// 		CommentDeleteResponse commentDeleteResponse = new CommentDeleteResponse(commentId, true);
//
// 		when(commentService.deleteComment(anyLong(), anyString())).thenReturn(commentDeleteResponse);
// 		//when
// 		ResultActions resultActions = mockMvc.perform(
// 			delete("/comment/{comment-id}", commentId)
// 				.header("userId", userId)
// 				.accept(MediaType.APPLICATION_JSON));
// 		//then
// 		resultActions.andExpect(status().isOk())
// 			.andDo(print())
// 			.andDo(document("delete-comment",
// 				pathParameters(parameterWithName("comment-id").description("수정할 bidding id")),
// 				responseFields(
// 					fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("삭제한 댓글 아이디"),
// 					fieldWithPath("state").type(JsonFieldType.BOOLEAN).description("댓글의 상태 false : 정상 true : 삭제됨")
// 				)
// 			));
// 	}
// }