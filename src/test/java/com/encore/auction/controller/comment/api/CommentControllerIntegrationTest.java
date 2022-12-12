// package com.encore.auction.controller.comment.api;
//
// import static org.junit.jupiter.api.Assertions.*;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.ResponseEntity;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.encore.auction.controller.comment.requests.CommentRegisterRequest;
// import com.encore.auction.controller.comment.requests.CommentUpdateRequest;
// import com.encore.auction.controller.comment.responses.CommentDeleteResponse;
// import com.encore.auction.controller.comment.responses.CommentDetailsResponse;
// import com.encore.auction.controller.comment.responses.CommentIdResponse;
//
// @SpringBootTest
// @Transactional
// class CommentControllerIntegrationTest {
//
// 	@Autowired
// 	private CommentController commentController;
//
// 	private final String userId = "tester1";
// 	private final Long auctionItemId = 1L;
// 	private final String content = "댓글 적어보자";
// 	private final Long commentId = 1L;
// 	private final String updateContent = "댓글 변경";
//
// 	@Test
// 	@DisplayName("Register Comment Integration Controller Test - Success")
// 	void registerCommentIntegrationSuccess() {
// 		//given
// 		CommentRegisterRequest commentRegisterRequest = new CommentRegisterRequest(userId, auctionItemId, content);
// 		//when
// 		ResponseEntity<CommentIdResponse> commentIdResponse = commentController.registerComment(commentRegisterRequest);
// 		//then
// 		assertNotNull(commentIdResponse.getBody().getCommentId());
// 	}
//
// 	@Test
// 	@DisplayName("Retrieve Comment Integration Controller Test - Success")
// 	void retrieveCommentIntegrationSuccess() {
// 		//given //when
// 		ResponseEntity<CommentDetailsResponse> commentDetailsResponse = commentController.retrieveComment(commentId);
// 		//then
// 		assertNotNull(commentDetailsResponse.getBody().getContent());
// 	}
//
// 	@Test
// 	@DisplayName("Update Comment Integration Controller Test - Success")
// 	void updateCommentIntegrationSuccess() {
// 		//given
// 		CommentUpdateRequest commentUpdateRequest = new CommentUpdateRequest(userId, auctionItemId, updateContent);
// 		//when
// 		ResponseEntity<CommentIdResponse> commentIdResponse = commentController.updateComment(commentId,
// 			commentUpdateRequest);
// 		//then
// 		assertNotNull(commentIdResponse.getBody().getCommentId());
// 	}
//
// 	@Test
// 	@DisplayName("Delete Comment Integration Controller Test - Success")
// 	void deleteCommentIntegrationSuccess() {
// 		//given //when
// 		ResponseEntity<CommentDeleteResponse> commentDeleteResponse = commentController.deleteComment(commentId,
// 			userId);
// 		//then
// 		assertTrue(commentDeleteResponse.getBody().isState());
// 	}
// }
