package com.encore.auction.utils.mapper;

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
}
