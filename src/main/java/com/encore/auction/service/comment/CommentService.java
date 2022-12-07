package com.encore.auction.service.comment;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.comment.requests.CommentRegisterRequest;
import com.encore.auction.controller.comment.requests.CommentUpdateRequest;
import com.encore.auction.controller.comment.responses.CommentDeleteResponse;
import com.encore.auction.controller.comment.responses.CommentDetailsResponse;
import com.encore.auction.controller.comment.responses.CommentIdResponse;

@Service
public class CommentService {
	public CommentIdResponse registerComment(CommentRegisterRequest commentRegisterRequest) {
		return null;
	}

	public CommentDetailsResponse retrieveComment(Long commentId) {
		return null;
	}

	public CommentIdResponse updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
		return null;
	}

	public CommentDeleteResponse deleteComment(Long commentId, String userId) {
		return null;
	}
}
