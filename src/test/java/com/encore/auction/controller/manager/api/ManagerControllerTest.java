package com.encore.auction.controller.manager.api;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.mockito.Mockito.*;
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

import com.encore.auction.controller.manager.requests.ManagerLoginRequest;
import com.encore.auction.controller.manager.requests.ManagerSignUpRequest;
import com.encore.auction.controller.manager.requests.ManagerUpdateRequest;
import com.encore.auction.controller.manager.responses.ManagerDeleteResponse;
import com.encore.auction.controller.manager.responses.ManagerDetailsResponse;
import com.encore.auction.controller.manager.responses.ManagerIdCheckResponse;
import com.encore.auction.controller.manager.responses.ManagerIdResponse;
import com.encore.auction.service.manager.ManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = ManagerController.class)
@AutoConfigureRestDocs
class ManagerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ManagerService managerService;

	@Autowired
	private ObjectMapper objectMapper;

	private final String managerId = "아이디정정2";

	private final String password = "비번과확인정정2";

	private final String passwordCheck = "비번과확인정정2";

	private final String name = "이름정정2";

	private final Integer age = 22;

	private final String nickname = "닉네임정일라이야";

	private final String phoneNumber = "01022222222";
	private final String email = "jj1234@gmail.com";

	@Test
	@DisplayName("Login Manager Controller Test - Success")
	void loginManagerSuccess() throws Exception {
		//given
		ManagerLoginRequest managerLoginRequest = new ManagerLoginRequest(managerId, password);
		ManagerIdResponse managerIdResponse = new ManagerIdResponse(managerId);

		when(managerService.loginManager(any(ManagerLoginRequest.class))).thenReturn(managerIdResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			post("/manager/login")
				.content(objectMapper.writeValueAsString(managerLoginRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("manager-login",
				requestFields(
					fieldWithPath("managerId").type(JsonFieldType.STRING).description("로그인 할 User Id"),
					fieldWithPath("password").type(JsonFieldType.STRING).description("로그인 할 User password")
				),
				responseFields(
					fieldWithPath("managerId").type(JsonFieldType.STRING).description("로그인 된 Manager Id")
				)
			));
	}

	@Test
	@DisplayName("Sign Up Manager Controller Test - Success")
	void signUpManagerSuccess() throws Exception {
		//given
		ManagerSignUpRequest managerSignUpRequest = new ManagerSignUpRequest(managerId, password, passwordCheck, name,
			age, phoneNumber, email);
		ManagerIdResponse managerIdResponse = new ManagerIdResponse(managerId);

		when(managerService.signUpManager(any(ManagerSignUpRequest.class))).thenReturn(managerIdResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			post("/manager/sign-up")
				.content(objectMapper.writeValueAsString(managerSignUpRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isCreated())
			.andDo(print())
			.andDo(document("manager-sign-up",
				requestFields(
					fieldWithPath("managerId").type(JsonFieldType.STRING).description("회원 가입할 매니저 아이디"),
					fieldWithPath("password").type(JsonFieldType.STRING).description("회원 가입할 매니저 password"),
					fieldWithPath("passwordCheck").type(JsonFieldType.STRING).description("비밀번호 체크"),
					fieldWithPath("name").type(JsonFieldType.STRING).description("회원가입할 매니저 이름"),
					fieldWithPath("age").type(JsonFieldType.NUMBER).description("회원 가입할 매니저 나이"),
					fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("회원 가입할 매니저 휴대폰 번호"),
					fieldWithPath("email").type(JsonFieldType.STRING).description("회원 가입할 매니저 이메일")
				),
				responseFields(
					fieldWithPath("managerId").type(JsonFieldType.STRING).description("회원 가입 된 매니저 아이디")
				)
			));
	}

	@Test
	@DisplayName("Update Manager Controller Test - Success")
	void updateManager() throws Exception {
		//given
		ManagerDetailsResponse managerDetailsResponse = new ManagerDetailsResponse(managerId, name, age,
			phoneNumber,
			email);
		ManagerUpdateRequest managerUpdateRequest = new ManagerUpdateRequest(password, "newPassword", "newPassword",
			name, age,
			phoneNumber, email);

		when(managerService.updateManager(anyString(), any(ManagerUpdateRequest.class))).thenReturn(
			managerDetailsResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			put("/manager/{manager-id}", managerId)
				.content(objectMapper.writeValueAsString(managerUpdateRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("update-manager",
				pathParameters(parameterWithName("manager-id").description("수정할 manager id")),
				requestFields(
					fieldWithPath("oldPassword").type(JsonFieldType.STRING).description("회원 가입할 매니저 password"),
					fieldWithPath("newPassword").type(JsonFieldType.STRING).description("회원 가입할 매니저 password"),
					fieldWithPath("passwordCheck").type(JsonFieldType.STRING).description("매니저 비밀번호 체크"),
					fieldWithPath("name").type(JsonFieldType.STRING).description("회원가입할 매니저 이름"),
					fieldWithPath("age").type(JsonFieldType.NUMBER).description("회원 가입할 매니저 나이"),
					fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("회원 가입할 매니저 휴대폰 번호"),
					fieldWithPath("email").type(JsonFieldType.STRING).description("회원 가입할 매니저 이메일")
				),
				responseFields(
					fieldWithPath("managerId").type(JsonFieldType.STRING).description("매니저 아이디"),
					fieldWithPath("name").type(JsonFieldType.STRING).description("매니저 이름"),
					fieldWithPath("age").type(JsonFieldType.NUMBER).description("매니저 나이"),
					fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("매니저 휴대폰 번호"),
					fieldWithPath("email").type(JsonFieldType.STRING).description("매니저 이메일")
				)
			));
	}

	@Test
	@DisplayName("Retrieve Manager Controller Test - Success")
	void retriveManager() throws Exception {
		//given
		ManagerDetailsResponse managerDetailsResponse = new ManagerDetailsResponse(managerId, name, age,
			phoneNumber,
			email);

		when(managerService.retrieveManager(anyString())).thenReturn(managerDetailsResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			get("/manager/{manager-id}", managerId)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("retrieve-manager",
				pathParameters(parameterWithName("manager-id").description("찾고 싶은 manager id")),
				responseFields(
					fieldWithPath("managerId").type(JsonFieldType.STRING).description("매니저 아이디"),
					fieldWithPath("name").type(JsonFieldType.STRING).description("매니저 이름"),
					fieldWithPath("age").type(JsonFieldType.NUMBER).description("매니저 나이"),
					fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("매니저 휴대폰 번호"),
					fieldWithPath("email").type(JsonFieldType.STRING).description("매니저 이메일")
				)
			));
	}

	@Test
	@DisplayName("Delete Manager Controller Test - Success")
	void deleteManager() throws Exception {
		//given
		ManagerDeleteResponse managerDeleteResponse = new ManagerDeleteResponse(managerId, true);

		when(managerService.deleteManager(anyString(), anyString())).thenReturn(managerDeleteResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			delete("/manager/{manager-id}", managerId)
				.header("password", password)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("delete-manager",
				pathParameters(parameterWithName("manager-id").description("탈퇴할 매니저 id")),
				responseFields(
					fieldWithPath("managerId").type(JsonFieldType.STRING).description("탈퇴한 매니저 아이디"),
					fieldWithPath("state").type(JsonFieldType.BOOLEAN).description("매니저 상태")
				)
			));
	}

	@Test
	@DisplayName("Check Manager Id Exist Or Not Controller Test - Success")
	void checkMangerIdExistOrNotSuccess() throws Exception {
		//given
		ManagerIdCheckResponse managerIdCheckResponse = new ManagerIdCheckResponse(false);

		when(managerService.checkManagerIdExist(anyString())).thenReturn(managerIdCheckResponse);
		//when
		ResultActions resultActions = mockMvc.perform(
			get("/manager/check-id-exist/{manager-id}", managerId)
				.accept(MediaType.APPLICATION_JSON));
		//then
		resultActions.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("check-id-exist-user",
				pathParameters(parameterWithName("manager-id").description("찾고 싶은 manager id")),
				responseFields(
					fieldWithPath("managerIdExist").type(JsonFieldType.BOOLEAN)
						.description("존재 하는지 여부 false : 존재하지 않음, true : 존재함")
				)
			));
	}

}


















