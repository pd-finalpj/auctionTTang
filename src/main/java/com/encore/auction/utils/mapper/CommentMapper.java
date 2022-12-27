package com.encore.auction.utils.mapper;

import com.encore.auction.controller.comment.requests.CommentRegisterRequest;
import com.encore.auction.controller.comment.responses.CommentDeleteResponse;
import com.encore.auction.controller.comment.responses.CommentDetailsResponse;
import com.encore.auction.controller.comment.responses.CommentIdResponse;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.comment.Comment;
import com.encore.auction.model.user.User;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentMapper {

	private static CommentMapper commentMapper = null;

	public static CommentMapper of() {
		if (commentMapper == null) {
			commentMapper = new CommentMapper();
		}
		return commentMapper;
	}

	public Comment registerRequestToEntity(CommentRegisterRequest commentRegisterRequest, User user,
		AuctionItem auctionItem) {
		return Comment.builder()
			.user(user)
			.auctionItem(auctionItem)
			.content(commentRegisterRequest.getContent())
			.state(false)
			.build();
	}

	public CommentIdResponse entityToCommentIdResponse(Comment savedComment) {
		return new CommentIdResponse(savedComment.getId());
	}

	public CommentDetailsResponse entityToCommentDetilasResponse(Comment comment) {
		return new CommentDetailsResponse(comment.getUser().getId(), comment.getAuctionItem().getId(),
			comment.getContent());
	}

	public CommentDeleteResponse entityToCommentDeleteResponse(Comment comment) {
		return new CommentDeleteResponse(comment.getId(), comment.getState());
	}
}
