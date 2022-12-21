package com.encore.auction.controller.comment.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.comment.requests.CommentRegisterRequest;
import com.encore.auction.controller.comment.requests.CommentUpdateRequest;
import com.encore.auction.controller.comment.responses.CommentDeleteResponse;
import com.encore.auction.controller.comment.responses.CommentDetailsResponse;
import com.encore.auction.controller.comment.responses.CommentIdResponse;
import com.encore.auction.service.comment.CommentService;
import com.encore.auction.utils.security.Permission;

@RestController
@RequestMapping("/1")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@Permission
	@PostMapping
	public ResponseEntity<CommentIdResponse> registerComment(@RequestHeader("Token") String token,
		@RequestBody CommentRegisterRequest commentRegisterRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(commentService.registerComment(token, commentRegisterRequest));
	}

	@GetMapping("/{comment-id}")
	public ResponseEntity<CommentDetailsResponse> retrieveComment(@PathVariable("comment-id") Long commentId) {
		return ResponseEntity.ok().body(commentService.retrieveComment(commentId));
	}

	@Permission
	@PutMapping("/{comment-id}")
	public ResponseEntity<CommentIdResponse> updateComment(@PathVariable("comment-id") Long commentId,
		@RequestHeader("Token") String token,
		@RequestBody CommentUpdateRequest commentUpdateRequest) {
		return ResponseEntity.ok().body(commentService.updateComment(commentId, token, commentUpdateRequest));
	}

	@Permission
	@DeleteMapping("/{comment-id}")
	public ResponseEntity<CommentDeleteResponse> deleteComment(@PathVariable("comment-id") Long commentId,
		@RequestHeader("Token") String token) {
		return ResponseEntity.ok().body(commentService.deleteComment(commentId, token));
	}
}
