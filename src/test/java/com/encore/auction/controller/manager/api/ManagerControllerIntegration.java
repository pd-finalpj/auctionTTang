package com.encore.auction.controller.manager.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.controller.manager.requests.ManagerLoginRequest;
import com.encore.auction.controller.manager.requests.ManagerSignUpRequest;
import com.encore.auction.controller.manager.requests.ManagerUpdateRequest;
import com.encore.auction.controller.manager.responses.ManagerDeleteResponse;
import com.encore.auction.controller.manager.responses.ManagerDetailsResponse;
import com.encore.auction.controller.manager.responses.ManagerIdCheckResponse;
import com.encore.auction.controller.manager.responses.ManagerIdResponse;

@SpringBootTest
@Transactional
class ManagerControllerIntegration {

	@Autowired
	private ManagerController managerController;

	private final String managerId = "tester2";
	private final String password = "tester2";

	private final String newManagerId = "newManagerTestId1";
	private final String newManagerPassword = "newManagerTestId1";
	private final String newManagerPasswordCheck = "newManagerTestId1";
	private final String newManagerName = "매니저테스트";
	private final Integer newManagerAge = 24;
	private final String newManagerPhoneNumber = "01022222222";
	private final String newManagerEmail = "ggwp@gmail.com";

	@Test
	@DisplayName("Login Manager Integration Controller Test - Success ")
	void loginManagerIntegrationSuccess() {
		//given
		ManagerLoginRequest managerLoginRequest = new ManagerLoginRequest(managerId, password);
		//when
		ResponseEntity<ManagerIdResponse> managerIdResponse = managerController.loginManager(managerLoginRequest);
		//then
		assertNotNull(managerIdResponse.getBody().getManagerId());
	}

	@Test
	@DisplayName("Sign Up Manager Integration Controller Test - Success")
	void signUpManagerIntegrationSuccess() {
		//given
		ManagerSignUpRequest managerSignUpRequest = new ManagerSignUpRequest(newManagerId, newManagerPassword,
			newManagerPasswordCheck,
			newManagerName, newManagerAge, newManagerPhoneNumber, newManagerEmail);
		//when
		ResponseEntity<ManagerIdResponse> managerIdResponse = managerController.signUpManager(managerSignUpRequest);
		//then
		assertNotNull(managerIdResponse.getBody().getManagerId());
	}

	@Test
	@DisplayName("Update Manager Integration Controller Test - Success")
	void updateManagerIntegrationSuccess() {
		//given
		ManagerUpdateRequest managerUpdateRequest = new ManagerUpdateRequest(password, newManagerPassword,
			newManagerPasswordCheck,
			newManagerName, newManagerAge, newManagerPhoneNumber, newManagerEmail);
		//when
		ResponseEntity<ManagerDetailsResponse> managerDetailsResponse = managerController.updateManager(managerId,
			managerUpdateRequest);
		//then
		assertNotNull(managerDetailsResponse.getBody().getManagerId());
	}

	@Test
	@DisplayName("Retrieve Manager Integration Controller Test - Success")
	void retrieveManagerIntegrationSuccess() {
		//given //when
		ResponseEntity<ManagerDetailsResponse> managerDetailsResponse = managerController.retrieveManager(managerId);
		//then
		assertNotNull(managerDetailsResponse.getBody().getManagerId());
	}

	@Test
	@DisplayName("Delete Manager Integration Controller Test - Success")
	void deleteManagerIntegrationSuccess() {
		//given //when
		ResponseEntity<ManagerDeleteResponse> managerDeleteResponse = managerController.deleteManager(managerId,
			password);
		//then
		assertTrue(managerDeleteResponse.getBody().isState());
	}

	@Test
	@DisplayName("Check Manager Id Exist Or Not Integration Controller Test - Success")
	void checkManagerIdExistOrNotIntegrationSuccess() {
		//given //when
		ResponseEntity<ManagerIdCheckResponse> managerIdCheckResponseExist = managerController.checkManagerIdExist(
			managerId);
		ResponseEntity<ManagerIdCheckResponse> managerIdCheckResponseNotExist = managerController.checkManagerIdExist(
			newManagerId);
		//then
		assertTrue(managerIdCheckResponseExist.getBody().isManagerIdExist());
		assertFalse(managerIdCheckResponseNotExist.getBody().isManagerIdExist());
	}
}
