package com.encore.auction.utils.mapper;

import com.encore.auction.controller.user.requests.UserSignUpRequest;
import com.encore.auction.controller.user.responses.UserDeleteResponse;
import com.encore.auction.controller.user.responses.UserDetailsResponse;
import com.encore.auction.controller.user.responses.UserIdResponse;
import com.encore.auction.model.user.User;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

	private static UserMapper userMapper = null;

	public static UserMapper of() {
		if (userMapper == null) {
			userMapper = new UserMapper();
		}
		return userMapper;
	}

	public UserIdResponse entityToUserIdResponse(User user) {
		return new UserIdResponse(user.getId());
	}

	public UserDetailsResponse entityToUserDetailsResponse(User user) {
		return new UserDetailsResponse(user.getId(), user.getName(), user.getBirth(), user.getNickname(),
			user.getPhoneNumber(), user.getEmail());
	}

	public User signUpRequestToEntity(UserSignUpRequest userSignUpRequest, String encryptedPassword, String newSalt) {
		return User.builder()
			.id(userSignUpRequest.getUserId())
			.password(encryptedPassword)
			.salt(newSalt)
			.name(userSignUpRequest.getName())
			.birth(userSignUpRequest.getBirth())
			.nickname(userSignUpRequest.getNickname())
			.phoneNumber(userSignUpRequest.getPhoneNumber())
			.email(userSignUpRequest.getEmail())
			.state(false)
			.build();
	}

	public UserDeleteResponse entityToUserDeleteResponse(User user) {
		return new UserDeleteResponse(user.getId(), user.getState());
	}
}
