package com.encore.auction.service.comment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.controller.comment.requests.CommentRegisterRequest;
import com.encore.auction.controller.comment.requests.CommentUpdateRequest;
import com.encore.auction.controller.comment.responses.CommentDeleteResponse;
import com.encore.auction.controller.comment.responses.CommentDetailsResponse;
import com.encore.auction.controller.comment.responses.CommentIdResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.exception.WrongRequestException;
import com.encore.auction.model.auction.item.AuctionItem;
import com.encore.auction.model.comment.Comment;
import com.encore.auction.model.user.User;
import com.encore.auction.repository.AuctionItemRepository;
import com.encore.auction.repository.CommentRepository;
import com.encore.auction.repository.UserRepository;
import com.encore.auction.utils.mapper.CommentMapper;
import com.encore.auction.utils.token.JwtProvider;

@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	private final AuctionItemRepository auctionItemRepository;
	private final JwtProvider jwtProvider;

	public CommentService(CommentRepository commentRepository, UserRepository userRepository,
		AuctionItemRepository auctionItemRepository, JwtProvider jwtProvider) {
		this.commentRepository = commentRepository;
		this.userRepository = userRepository;
		this.auctionItemRepository = auctionItemRepository;
		this.jwtProvider = jwtProvider;
	}

	@Transactional
	public CommentIdResponse registerComment(String token, CommentRegisterRequest commentRegisterRequest) {
		String userId = checkTokenIsUserAndGetUserID(token);

		User user = checkUserExistAndGetUser(userId);

		AuctionItem auctionItem = auctionItemRepository.findById(commentRegisterRequest.getAuctionItemId())
			.orElseThrow(() -> new NonExistResourceException("Auction Item does not exist"));

		Comment comment = CommentMapper.of().registerRequestToEntity(commentRegisterRequest, user, auctionItem);

		Comment savedComment = commentRepository.save(comment);

		return CommentMapper.of().entityToCommentIdResponse(savedComment);
	}

	public CommentDetailsResponse retrieveComment(Long commentId) {
		Comment comment = checkCommentExistAndGetComment(commentId);

		return CommentMapper.of().entityToCommentDetilasResponse(comment);
	}

	@Transactional
	public CommentIdResponse updateComment(Long commentId, String token, CommentUpdateRequest commentUpdateRequest) {
		String userId = checkTokenIsUserAndGetUserID(token);

		User user = checkUserExistAndGetUser(userId);

		Comment comment = checkCommentExistAndGetComment(commentId);

		if (checkUserNotMatch(user.getId(), comment))
			throw new WrongRequestException("User does not match");

		comment.updateComment(commentUpdateRequest);

		return CommentMapper.of().entityToCommentIdResponse(comment);
	}

	@Transactional
	public CommentDeleteResponse deleteComment(Long commentId, String token) {
		String userId = checkTokenIsUserAndGetUserID(token);

		User user = checkUserExistAndGetUser(userId);

		Comment comment = checkCommentExistAndGetComment(commentId);

		if (checkUserNotMatch(user.getId(), comment))
			throw new WrongRequestException("User does not match");

		comment.deleteComment();

		return CommentMapper.of().entityToCommentDeleteResponse(comment);
	}

	private boolean checkUserNotMatch(String userId, Comment comment) {
		return !comment.getUser().getId().equals(userId);
	}

	private User checkUserExistAndGetUser(String userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User does not exist"));
	}

	private Comment checkCommentExistAndGetComment(Long commentId) {
		return commentRepository.findById(commentId)
			.orElseThrow(() -> new NonExistResourceException("Comment does not exist"));
	}

	private String checkTokenIsUserAndGetUserID(String token) {
		if (jwtProvider.getAudience(token).equals("manager"))
			throw new WrongRequestException("Manager Token can't do user's thing");
		return jwtProvider.getSubject(token);
	}
}
