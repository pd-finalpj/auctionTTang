package com.encore.auction.controller.user.api;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.encore.auction.controller.user.requests.UserLoginRequest;
import com.encore.auction.controller.user.responses.UserIdResponse;
import com.encore.auction.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureRestDocs
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	private final String userId = "tester1";
	private final String password = "tester123";

	@Test
	@DisplayName("Login User Controller Test - Success ")
	void loginUserSuccess() throws Exception {
		//given
		UserLoginRequest userLoginRequest = new UserLoginRequest(userId, password);
		UserIdResponse userIdResponse = new UserIdResponse(userId);

		when(userService.loginUser(any(UserLoginRequest.class))).thenReturn(userIdResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			post("/user/login")
				.content(objectMapper.writeValueAsString(userLoginRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("user-login",
				requestFields(
					fieldWithPath("userId").type(JsonFieldType.STRING).description("로그인 할 User Id"),
					fieldWithPath("password").type(JsonFieldType.STRING).description("로그인 할 User password")
				),
				responseFields(
					fieldWithPath("userId").type(JsonFieldType.STRING).description("로그인 된 User Id")
				)
			));
	}
}