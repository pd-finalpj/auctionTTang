package com.encore.auction.controller.validation.api;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.encore.auction.controller.user.api.UserController;
import com.encore.auction.controller.user.requests.UserLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class ValidationExceptionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserController userController;

	@Autowired
	private ObjectMapper objectMapper;

	private final String userId = "tester1";
	private final String password = "tester1";

	@Test
	@DisplayName("Login User Validation Integration Controller Test - Fail ")
	void loginUserIntegrationSuccess() throws Exception {
		//given
		UserLoginRequest userLoginRequest = new UserLoginRequest(userId, password);
		//when
		ResultActions resultActions = mockMvc.perform(
			post("/user/login")
				.content(objectMapper.writeValueAsString(userLoginRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}
}
