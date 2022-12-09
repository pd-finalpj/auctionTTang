package com.encore.auction.controller.user.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.controller.user.requests.UserLoginRequest;
import com.encore.auction.controller.user.requests.UserSignUpRequest;
import com.encore.auction.controller.user.requests.UserUpdateRequest;
import com.encore.auction.controller.user.responses.UserDeleteResponse;
import com.encore.auction.controller.user.responses.UserDetailsResponse;
import com.encore.auction.controller.user.responses.UserIdCheckResponse;
import com.encore.auction.controller.user.responses.UserIdResponse;

@SpringBootTest
@Transactional
public class UserControllerIntegrationTest {

	@Autowired
	private UserController userController;

	private final String userId = "tester1";
	private final String password = "tester1";

	private final String newUserId = "newUserTestId1";
	private final String newUserPassword = "newUserTestId1";
	private final String newUserPasswordCheck = "newUserTestId1";
	private final String newUserName = "테스트";
	private final Integer newUserAge = 25;
	private final String newUserNickname = "테스트계정입니다";
	private final String newUserPhoneNumber = "01098006069";
	private final String newUserEmail = "say02v@naver.com";

	@Test
	@DisplayName("Login User Integration Controller Test - Success ")
	void loginUserIntegrationSuccess() {
		//given
		UserLoginRequest userLoginRequest = new UserLoginRequest(userId, password);
		//when
		ResponseEntity<UserIdResponse> userIdResponse = userController.loginUser(userLoginRequest);
		//then
		assertNotNull(userIdResponse.getBody().getUserId());
	}

	@Test
	@DisplayName("Sign Up User Integration Controller Test - Success")
	void signUpUserIntegrationSuccess() {
		//given
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest(newUserId, newUserPassword, newUserPasswordCheck,
			newUserName, newUserAge, newUserNickname, newUserPhoneNumber, newUserEmail);
		//when
		ResponseEntity<UserIdResponse> userIdResponse = userController.signUpUser(userSignUpRequest);
		//then
		assertNotNull(userIdResponse.getBody().getUserId());
	}

	@Test
	@DisplayName("Update User Integration Controller Test - Success")
	void updateUserIntegrationSuccess() {
		//given
		UserUpdateRequest userUpdateRequest = new UserUpdateRequest(password, newUserPassword, newUserPasswordCheck,
			newUserName, newUserAge, newUserNickname, newUserPhoneNumber, newUserEmail);
		//when
		ResponseEntity<UserDetailsResponse> userDetailsResponse = userController.updateUser(userId, userUpdateRequest);
		//then
		assertNotNull(userDetailsResponse.getBody().getUserId());
	}

	@Test
	@DisplayName("Retrieve User Integration Controller Test - Success")
	void retrieveUserIntegrationSuccess() {
		//given //when
		ResponseEntity<UserDetailsResponse> userDetailsResponse = userController.retrieveUser(userId);
		//then
		assertNotNull(userDetailsResponse.getBody().getUserId());
	}

	@Test
	@DisplayName("Delete User Integration Controller Test - Success")
	void deleteUserIntegrationSuccess() {
		//given //when
		ResponseEntity<UserDeleteResponse> userDeleteResponse = userController.deleteUser(userId, password);
		//then
		assertTrue(userDeleteResponse.getBody().isState());
	}

	@Test
	@DisplayName("Check User Id Exist Or Not Integration Controller Test - Success")
	void checkUserIdExistOrNotIntegrationSuccess() {
		//given //when
		ResponseEntity<UserIdCheckResponse> userIdCheckResponseExist = userController.checkUserIdExist(userId);
		ResponseEntity<UserIdCheckResponse> userIdCheckResponseNotExist = userController.checkUserIdExist(newUserId);
		//then
		assertTrue(userIdCheckResponseExist.getBody().isUserIdExist());
		assertFalse(userIdCheckResponseNotExist.getBody().isUserIdExist());
	}
}
