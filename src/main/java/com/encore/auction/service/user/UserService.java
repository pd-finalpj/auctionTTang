package com.encore.auction.service.user;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.user.requests.UserLoginRequest;
import com.encore.auction.controller.user.requests.UserSiginUpRequest;
import com.encore.auction.controller.user.requests.UserUpdateRequest;
import com.encore.auction.controller.user.responses.UserDeleteResponse;
import com.encore.auction.controller.user.responses.UserDetailsResponse;
import com.encore.auction.controller.user.responses.UserIdResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.exception.WrongRequestException;
import com.encore.auction.model.user.User;
import com.encore.auction.repository.UserRepository;
import com.encore.auction.utils.encrypt.Encrypt;
import com.encore.auction.utils.mapper.UserMapper;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserIdResponse loginUser(UserLoginRequest userLoginRequest) {
		User user = userRepository.findById(userLoginRequest.getUserId())
			.orElseThrow(() -> new NonExistResourceException("User does not exist"));

		if (!isUserPasswordCorrect(userLoginRequest.getPassword(), user))
			throw new WrongRequestException("User password in correct");

		return UserMapper.of().entityToUserIdResponse(user);
	}

	private boolean isUserPasswordCorrect(String password, User user) {
		return user.getPassword().equals(Encrypt.of().getEncrypt(password, user.getSalt()));
	}

	public UserDetailsResponse retrieveUser(String userId) {
		return null;
	}

	public UserIdResponse signUpUser(UserSiginUpRequest userSiginUpRequest) {
		return null;
	}

	public UserDetailsResponse updateUser(String userId, UserUpdateRequest userUpdateRequest) {
		return null;
	}

	public UserDeleteResponse deleteUser(String userId, String password) {
		return null;
	}
}
