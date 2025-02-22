// package com.encore.auction.controller.user.api;
//
// import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
// import static org.mockito.Mockito.*;
// import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
// import static org.springframework.restdocs.payload.PayloadDocumentation.*;
// import static org.springframework.restdocs.request.RequestDocumentation.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.restdocs.payload.JsonFieldType;
// import org.springframework.test.context.web.WebAppConfiguration;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.ResultActions;
//
// import com.encore.auction.controller.user.requests.UserLoginRequest;
// import com.encore.auction.controller.user.requests.UserSignUpRequest;
// import com.encore.auction.controller.user.requests.UserUpdateRequest;
// import com.encore.auction.controller.user.responses.UserDeleteResponse;
// import com.encore.auction.controller.user.responses.UserDetailsResponse;
// import com.encore.auction.controller.user.responses.UserIdCheckResponse;
// import com.encore.auction.controller.user.responses.UserIdResponse;
// import com.encore.auction.controller.user.responses.UserTokenResponse;
// import com.encore.auction.service.user.UserService;
// import com.fasterxml.jackson.databind.ObjectMapper;
//
// @WebMvcTest(controllers = UserController.class)
// @AutoConfigureRestDocs
// @WebAppConfiguration
// class UserControllerTest {
//
// 	@Autowired
// 	private MockMvc mockMvc;
//
// 	@MockBean
// 	private UserService userService;
//
// 	@Autowired
// 	private ObjectMapper objectMapper;
//
// 	private final String userId = "tester1";
// 	private final String password = "tester1!";
// 	private final String passwordCheck = "tester1!";
// 	private final String newPassword = "tester2!";
// 	private final String newPasswordCheck = "tester2!";
// 	private final String name = "정정일";
// 	private final Integer age = 25;
// 	private final String nickname = "정일짱짱맨";
// 	private final String phoneNumber = "01098006069";
// 	private final String email = "jji042842@gmail.com";
// 	private final String token = "userToken";
//
// 	@Test
// 	@DisplayName("Login User Controller Test - Success ")
// 	void loginUserSuccess() throws Exception {
// 		//given
// 		UserLoginRequest userLoginRequest = new UserLoginRequest(userId, password);
// 		UserTokenResponse userTokenResponse = new UserTokenResponse("accessToken", "tokenType");
//
// 		when(userService.loginUser(any(UserLoginRequest.class))).thenReturn(userTokenResponse);
// 		//when
// 		ResultActions resultActions = mockMvc.perform(
// 			post("/user/login")
// 				.content(objectMapper.writeValueAsString(userLoginRequest))
// 				.contentType(MediaType.APPLICATION_JSON)
// 				.accept(MediaType.APPLICATION_JSON));
// 		//then
// 		resultActions.andExpect(status().isOk())
// 			.andDo(print())
// 			.andDo(document("user-login",
// 				requestFields(
// 					fieldWithPath("userId").type(JsonFieldType.STRING).description("로그인 할 User Id"),
// 					fieldWithPath("password").type(JsonFieldType.STRING).description("로그인 할 User password")
// 				),
// 				responseFields(
// 					fieldWithPath("accessToken").type(JsonFieldType.STRING).description("로그인 된 User 토큰"),
// 					fieldWithPath("tokenType").type(JsonFieldType.STRING).description("토큰 타입")
// 				)
// 			));
// 	}
//
// 	@Test
// 	@DisplayName("Sign Up User Controller Test - Success")
// 	void signUpUserSuccess() throws Exception {
// 		//given
// 		UserSignUpRequest userSignUpRequest = new UserSignUpRequest(userId, password, passwordCheck, name, age,
// 			nickname, phoneNumber, email);
// 		UserIdResponse userIdResponse = new UserIdResponse(userId);
//
// 		when(userService.signUpUser(any(UserSignUpRequest.class))).thenReturn(userIdResponse);
// 		//when
// 		ResultActions resultActions = mockMvc.perform(
// 			post("/user/sign-up")
// 				.content(objectMapper.writeValueAsString(userSignUpRequest))
// 				.contentType(MediaType.APPLICATION_JSON)
// 				.accept(MediaType.APPLICATION_JSON));
// 		//then
// 		resultActions.andExpect(status().isCreated())
// 			.andDo(print())
// 			.andDo(document("user-sign-up",
// 				requestFields(
// 					fieldWithPath("userId").type(JsonFieldType.STRING).description("회원 가입할 유저 아이디"),
// 					fieldWithPath("password").type(JsonFieldType.STRING).description("회원 가입할 유저 password"),
// 					fieldWithPath("passwordCheck").type(JsonFieldType.STRING).description("비밀번호 체크"),
// 					fieldWithPath("name").type(JsonFieldType.STRING).description("회원가입할 유저 이름"),
// 					fieldWithPath("age").type(JsonFieldType.NUMBER).description("회원 가입할 유저 나이"),
// 					fieldWithPath("nickname").type(JsonFieldType.STRING).description("회원 가입할 유저 닉네임"),
// 					fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("회원 가입할 유저 휴대폰 번호"),
// 					fieldWithPath("email").type(JsonFieldType.STRING).description("회원 가입할 유저 이메일")
// 				),
// 				responseFields(
// 					fieldWithPath("userId").type(JsonFieldType.STRING).description("회원 가입 된 유저 아이디")
// 				)
// 			));
// 	}
//
// 	@Test
// 	@DisplayName("Update User Controller Test - Success")
// 	void updateUserSuccess() throws Exception {
// 		//given
// 		UserDetailsResponse userDetailsResponse = new UserDetailsResponse(userId, name, age, nickname, phoneNumber,
// 			email);
// 		UserUpdateRequest userUpdateRequest = new UserUpdateRequest(password, newPassword, newPasswordCheck, name, age,
// 			nickname,
// 			phoneNumber, email);
//
// 		when(userService.updateUser(anyString(), any(UserUpdateRequest.class))).thenReturn(userDetailsResponse);
// 		//when
// 		ResultActions resultActions = mockMvc.perform(
// 			put("/user/{user-id}", userId)
// 				.content(objectMapper.writeValueAsString(userUpdateRequest))
// 				.header("Token", token)
// 				.contentType(MediaType.APPLICATION_JSON)
// 				.accept(MediaType.APPLICATION_JSON));
// 		//then
// 		resultActions.andExpect(status().isOk())
// 			.andDo(print())
// 			.andDo(document("update-user",
// 				pathParameters(parameterWithName("user-id").description("수정할 user id")),
// 				requestFields(
// 					fieldWithPath("oldPassword").type(JsonFieldType.STRING).description("회원 가입할 유저 password"),
// 					fieldWithPath("newPassword").type(JsonFieldType.STRING).description("회원 가입할 유저 password"),
// 					fieldWithPath("passwordCheck").type(JsonFieldType.STRING).description("비밀번호 체크"),
// 					fieldWithPath("name").type(JsonFieldType.STRING).description("회원가입할 유저 이름"),
// 					fieldWithPath("age").type(JsonFieldType.NUMBER).description("회원 가입할 유저 나이"),
// 					fieldWithPath("nickname").type(JsonFieldType.STRING).description("회원 가입할 유저 닉네임"),
// 					fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("회원 가입할 유저 휴대폰 번호"),
// 					fieldWithPath("email").type(JsonFieldType.STRING).description("회원 가입할 유저 이메일")
// 				),
// 				responseFields(
// 					fieldWithPath("userId").type(JsonFieldType.STRING).description("유저 아이디"),
// 					fieldWithPath("name").type(JsonFieldType.STRING).description("유저 이름"),
// 					fieldWithPath("age").type(JsonFieldType.NUMBER).description("유저 나이"),
// 					fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
// 					fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("유저 휴대폰 번호"),
// 					fieldWithPath("email").type(JsonFieldType.STRING).description("유저 이메일")
// 				)
// 			));
// 	}
//
// 	@Test
// 	@DisplayName("Retrieve User Controller Test - Success")
// 	void retrieveUserSuccess() throws Exception {
// 		//given
// 		UserDetailsResponse userDetailsResponse = new UserDetailsResponse(userId, name, age, nickname, phoneNumber,
// 			email);
//
// 		when(userService.retrieveUser(anyString())).thenReturn(userDetailsResponse);
// 		//when
// 		ResultActions resultActions = mockMvc.perform(
// 			get("/user/{user-id}", userId)
// 				.header("Token", token)
// 				.accept(MediaType.APPLICATION_JSON));
// 		//then
// 		resultActions.andExpect(status().isOk())
// 			.andDo(print())
// 			.andDo(document("retrieve-user",
// 				pathParameters(parameterWithName("user-id").description("찾고 싶은 user id")),
// 				responseFields(
// 					fieldWithPath("userId").type(JsonFieldType.STRING).description("유저 아이디"),
// 					fieldWithPath("name").type(JsonFieldType.STRING).description("유저 이름"),
// 					fieldWithPath("age").type(JsonFieldType.NUMBER).description("유저 나이"),
// 					fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
// 					fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("유저 휴대폰 번호"),
// 					fieldWithPath("email").type(JsonFieldType.STRING).description("유저 이메일")
// 				)
// 			));
// 	}
//
// 	@Test
// 	@DisplayName("Delete User Controller Test - Success")
// 	void deleteUserSuccess() throws Exception {
// 		//given
// 		UserDeleteResponse userDeleteResponse = new UserDeleteResponse(userId, true);
//
// 		when(userService.deleteUser(anyString(), anyString())).thenReturn(userDeleteResponse);
// 		//when
// 		ResultActions resultActions = mockMvc.perform(
// 			delete("/user/{user-id}", userId)
// 				.header("Token", token)
// 				.accept(MediaType.APPLICATION_JSON));
// 		//then
// 		resultActions.andExpect(status().isOk())
// 			.andDo(print())
// 			.andDo(document("delete-user",
// 				pathParameters(parameterWithName("user-id").description("탈퇴할 user id")),
// 				responseFields(
// 					fieldWithPath("userId").type(JsonFieldType.STRING).description("탈퇴한 유저 아이디"),
// 					fieldWithPath("state").type(JsonFieldType.BOOLEAN).description("유저 상태")
// 				)
// 			));
// 	}
//
// 	@Test
// 	@DisplayName("Check User Id Exist Or Not Controller Test - Success")
// 	void checkUserIdExistOrNotSuccess() throws Exception {
// 		//given
// 		UserIdCheckResponse userIdCheckResponse = new UserIdCheckResponse(false);
//
// 		when(userService.checkUserIdExist(anyString())).thenReturn(userIdCheckResponse);
// 		//when
// 		ResultActions resultActions = mockMvc.perform(
// 			get("/user/check-id-exist/{user-id}", userId)
// 				.accept(MediaType.APPLICATION_JSON));
// 		//then
// 		resultActions.andExpect(status().isOk())
// 			.andDo(print())
// 			.andDo(document("check-id-exist-user",
// 				pathParameters(parameterWithName("user-id").description("찾고 싶은 user id")),
// 				responseFields(
// 					fieldWithPath("userIdExist").type(JsonFieldType.BOOLEAN)
// 						.description("존재 하는지 여부 false : 존재하지 않음, true : 존재함")
// 				)
// 			));
// 	}
//
// }