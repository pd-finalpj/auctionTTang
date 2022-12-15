package com.encore.auction.service.user;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.controller.user.requests.UserLoginRequest;
import com.encore.auction.controller.user.requests.UserSignUpRequest;
import com.encore.auction.controller.user.requests.UserUpdateRequest;
import com.encore.auction.controller.user.responses.UserDeleteResponse;
import com.encore.auction.controller.user.responses.UserDetailsResponse;
import com.encore.auction.controller.user.responses.UserIdCheckResponse;
import com.encore.auction.controller.user.responses.UserIdResponse;
import com.encore.auction.controller.user.responses.UserTokenResponse;
import com.encore.auction.exception.NonExistResourceException;
import com.encore.auction.exception.WrongRequestException;
import com.encore.auction.model.user.User;
import com.encore.auction.repository.user.UserRepository;
import com.encore.auction.utils.encrypt.Encrypt;
import com.encore.auction.utils.mapper.UserMapper;
import com.encore.auction.utils.token.JwtProvider;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;

	public UserService(UserRepository userRepository, JwtProvider jwtProvider) {
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
	}

	public UserTokenResponse loginUser(UserLoginRequest userLoginRequest) {
		User user = checkUserExistAndCheckPasswordIsCorrect(userLoginRequest.getUserId(),
			userLoginRequest.getPassword());

		String token = jwtProvider.createToken(user.getId(), "user");

		return new UserTokenResponse(token, "Bearer");
	}

	//
	public UserDetailsResponse retrieveUser(String token) {
		String userId = checkTokenIsUserAndGetUserID(token);

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new NonExistResourceException("User does not exist"));

		return UserMapper.of().entityToUserDetailsResponse(user);
	}

	@Transactional
	public UserIdResponse signUpUser(UserSignUpRequest userSignUpRequest) {
		Optional<User> user = userRepository.findById(userSignUpRequest.getUserId());

		if (user.isPresent())
			throw new WrongRequestException("User Id already existed");

		if (!userSignUpRequest.getPassword().equals(userSignUpRequest.getPasswordCheck()))
			throw new WrongRequestException("User Password is incorrect with Password check");

		String newSalt = Encrypt.of().getSalt();

		String encryptedPassword = Encrypt.of().getEncrypt(userSignUpRequest.getPassword(), newSalt);

		User newUser = UserMapper.of().signUpRequestToEntity(userSignUpRequest, encryptedPassword, newSalt);

		User savedUser = userRepository.save(newUser);

		return UserMapper.of().entityToUserIdResponse(savedUser);
	}

	@Transactional
	public UserDetailsResponse updateUser(String token, UserUpdateRequest userUpdateRequest) {
		String userId = checkTokenIsUserAndGetUserID(token);

		User user = checkUserExistAndCheckPasswordIsCorrect(userId, userUpdateRequest.getOldPassword());

		if (!userUpdateRequest.getNewPassword().equals(userUpdateRequest.getPasswordCheck()))
			throw new WrongRequestException("User Password is incorrect with Password check");

		String newSalt = Encrypt.of().getSalt();

		String encryptedPassword = Encrypt.of().getEncrypt(userUpdateRequest.getNewPassword(), newSalt);

		user.updateUser(userUpdateRequest, encryptedPassword, newSalt);

		return UserMapper.of().entityToUserDetailsResponse(user);
	}

	public UserDeleteResponse deleteUser(String token, String password) {
		String userId = checkTokenIsUserAndGetUserID(token);

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

	public UserIdCheckResponse checkUserIdExist(String userId) {
		return new UserIdCheckResponse(userRepository.findById(userId).isPresent());
	}

	private String checkTokenIsUserAndGetUserID(String token) {
		if (jwtProvider.getAudience(token).equals("manager"))
			throw new WrongRequestException("Manager Token can't do user's thing");
		return jwtProvider.getSubject(token);
	}
}