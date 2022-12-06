package com.encore.auction.controller.user.api;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
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
import com.encore.auction.controller.user.requests.UserSiginUpRequest;
import com.encore.auction.controller.user.requests.UserUpdateRequest;
import com.encore.auction.controller.user.responses.UserDeleteResponse;
import com.encore.auction.controller.user.responses.UserDetailsResponse;
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
	private final String passwordCheck = "tester123";
	private final String name = "정정일";
	private final Integer age = 25;
	private final String nickname = "정일짱짱맨";
	private final String phoneNumber = "01098006069";
	private final String email = "jji042842@gmail.com";

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

	@Test
	@DisplayName("Sign Up User Controller Test - Success")
	void signUpUserSuccess() throws Exception {
		//given
		UserSiginUpRequest userSiginUpRequest = new UserSiginUpRequest(userId, password, passwordCheck, name, age,
			nickname, phoneNumber, email);
		UserIdResponse userIdResponse = new UserIdResponse(userId);

		when(userService.signUpUser(any(UserSiginUpRequest.class))).thenReturn(userIdResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			post("/user/sign-up")
				.content(objectMapper.writeValueAsString(userSiginUpRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isCreated())
			.andDo(print())
			.andDo(document("user-sign-up",
				requestFields(
					fieldWithPath("userId").type(JsonFieldType.STRING).description("회원 가입할 유저 아이디"),
					fieldWithPath("password").type(JsonFieldType.STRING).description("회원 가입할 유저 password"),
					fieldWithPath("passwordCheck").type(JsonFieldType.STRING).description("비밀번호 체크"),
					fieldWithPath("name").type(JsonFieldType.STRING).description("회원가입할 유저 이름"),
					fieldWithPath("age").type(JsonFieldType.NUMBER).description("회원 가입할 유저 나이"),
					fieldWithPath("nickname").type(JsonFieldType.STRING).description("회원 가입할 유저 닉네임"),
					fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("회원 가입할 유저 휴대폰 번호"),
					fieldWithPath("email").type(JsonFieldType.STRING).description("회원 가입할 유저 이메일")
				),
				responseFields(
					fieldWithPath("userId").type(JsonFieldType.STRING).description("회원 가입 된 유저 아이디")
				)
			));
	}

	@Test
	@DisplayName("Update User Controller Test - Success")
	void updateUser() throws Exception {
		//given
		UserDetailsResponse userDetailsResponse = new UserDetailsResponse(userId, name, age, nickname, phoneNumber,
			email);
		UserUpdateRequest userUpdateRequest = new UserUpdateRequest(password, "newPassword", "newPassword", name, age,
			nickname,
			phoneNumber, email);

		when(userService.updateUser(anyString(), any(UserUpdateRequest.class))).thenReturn(userDetailsResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			put("/user/{user-id}", userId)
				.content(objectMapper.writeValueAsString(userUpdateRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("update-user",
				pathParameters(parameterWithName("user-id").description("수정할 user id")),
				requestFields(
					fieldWithPath("oldPassword").type(JsonFieldType.STRING).description("회원 가입할 유저 password"),
					fieldWithPath("newPassword").type(JsonFieldType.STRING).description("회원 가입할 유저 password"),
					fieldWithPath("passwordCheck").type(JsonFieldType.STRING).description("비밀번호 체크"),
					fieldWithPath("name").type(JsonFieldType.STRING).description("회원가입할 유저 이름"),
					fieldWithPath("age").type(JsonFieldType.NUMBER).description("회원 가입할 유저 나이"),
					fieldWithPath("nickname").type(JsonFieldType.STRING).description("회원 가입할 유저 닉네임"),
					fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("회원 가입할 유저 휴대폰 번호"),
					fieldWithPath("email").type(JsonFieldType.STRING).description("회원 가입할 유저 이메일")
				),
				responseFields(
					fieldWithPath("userId").type(JsonFieldType.STRING).description("유저 아이디"),
					fieldWithPath("name").type(JsonFieldType.STRING).description("유저 이름"),
					fieldWithPath("age").type(JsonFieldType.NUMBER).description("유저 나이"),
					fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
					fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("유저 휴대폰 번호"),
					fieldWithPath("email").type(JsonFieldType.STRING).description("유저 이메일")
				)
			));
	}

	@Test
	@DisplayName("Retrieve User Controller Test - Success")
	void retrieveUser() throws Exception {
		//given
		UserDetailsResponse userDetailsResponse = new UserDetailsResponse(userId, name, age, nickname, phoneNumber,
			email);

		when(userService.retrieveUser(anyString())).thenReturn(userDetailsResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			get("/user/{user-id}", userId)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("retrieve-user",
				pathParameters(parameterWithName("user-id").description("찾고 싶은 user id")),
				responseFields(
					fieldWithPath("userId").type(JsonFieldType.STRING).description("유저 아이디"),
					fieldWithPath("name").type(JsonFieldType.STRING).description("유저 이름"),
					fieldWithPath("age").type(JsonFieldType.NUMBER).description("유저 나이"),
					fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
					fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("유저 휴대폰 번호"),
					fieldWithPath("email").type(JsonFieldType.STRING).description("유저 이메일")
				)
			));
	}

	@Test
	@DisplayName("Delete User Controller Test - Success")
	void deleteUser() throws Exception {
		//given
		UserDeleteResponse userDeleteResponse = new UserDeleteResponse(userId, true);

		when(userService.deleteUser(anyString(), anyString())).thenReturn(userDeleteResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			delete("/user/{user-id}", userId)
				.header("password", password)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("delete-user",
				pathParameters(parameterWithName("user-id").description("탈퇴할 user id")),
				responseFields(
					fieldWithPath("userId").type(JsonFieldType.STRING).description("탈퇴한 유저 아이디"),
					fieldWithPath("state").type(JsonFieldType.BOOLEAN).description("유저 상태")
				)
			));
	}

}