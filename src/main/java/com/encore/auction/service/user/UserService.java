package com.encore.auction.service.user;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		User user = checkUserExistAndCheckPasswordIsCorrect(userLoginRequest.getUserId(),
			userLoginRequest.getPassword());

		return UserMapper.of().entityToUserIdResponse(user);
	}
	//
	public UserDetailsResponse retrieveUser(String userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User does not exist"));

		return UserMapper.of().entityToUserDetailsResponse(user);
	}

	@Transactional
	public UserIdResponse signUpUser(UserSiginUpRequest userSiginUpRequest) {
		Optional<User> user = userRepository.findById(userSiginUpRequest.getUserId());

		if (user.isPresent())
			throw new WrongRequestException("User Id already existed");

		if (userSiginUpRequest.getPassword().equals(userSiginUpRequest.getPasswordCheck()))
			throw new WrongRequestException("User Password is incorrect with Password check");

		String newSalt = Encrypt.of().getSalt();

		String encryptedPassword = Encrypt.of().getEncrypt(userSiginUpRequest.getPassword(), newSalt);

		User newUser = UserMapper.of().signUpRequestToEntity(userSiginUpRequest, encryptedPassword, newSalt);

		User savedUser = userRepository.save(newUser);

		return UserMapper.of().entityToUserIdResponse(savedUser);
	}

	@Transactional
	public UserDetailsResponse updateUser(String userId, UserUpdateRequest userUpdateRequest) {
		User user = checkUserExistAndCheckPasswordIsCorrect(userId, userUpdateRequest.getOldPassword());

		if (!userUpdateRequest.getNewPassword().equals(userUpdateRequest.getPasswordCheck()))
			throw new WrongRequestException("User Password is incorrect with Password check");

		String newSalt = Encrypt.of().getSalt();

		String encryptedPassword = Encrypt.of().getEncrypt(userUpdateRequest.getNewPassword(), newSalt);

		user.updateUser(userUpdateRequest, encryptedPassword, newSalt);

		return UserMapper.of().entityToUserDetailsResponse(user);
	}

	public UserDeleteResponse deleteUser(String userId, String password) {
		User user = checkUserExistAndCheckPasswordIsCorrect(userId, password);

		user.deleteUser();

		return UserMapper.of().entityToUserDeleteResponse(user);
	}

	private User checkUserExistAndCheckPasswordIsCorrect(String userId, String userPassword) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User does not exist"));

		if (!isUserPasswordCorrect(userPassword, user))
			throw new WrongRequestException("User password in correct");
		return user;
	}

	private boolean isUserPasswordCorrect(String inputPassword, User user) {
		return user.getPassword().equals(Encrypt.of().getEncrypt(inputPassword, user.getSalt()));
	}
}
