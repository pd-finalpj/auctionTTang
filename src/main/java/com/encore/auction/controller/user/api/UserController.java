package com.encore.auction.controller.user.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.user.requests.UserLoginRequest;
import com.encore.auction.controller.user.requests.UserSignUpRequest;
import com.encore.auction.controller.user.requests.UserUpdateRequest;
import com.encore.auction.controller.user.responses.UserDeleteResponse;
import com.encore.auction.controller.user.responses.UserDetailsResponse;
import com.encore.auction.controller.user.responses.UserIdCheckResponse;
import com.encore.auction.controller.user.responses.UserIdResponse;
import com.encore.auction.controller.user.responses.UserTokenResponse;
import com.encore.auction.service.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/check-id-exist/{user-id}")
	public ResponseEntity<UserIdCheckResponse> checkUserIdExist(@PathVariable("user-id") String userId) {
		return ResponseEntity.ok().body(userService.checkUserIdExist(userId));
	}

	@PostMapping("/login")
	public ResponseEntity<UserTokenResponse> loginUser(@Valid @RequestBody UserLoginRequest userLoginRequest) {
		return ResponseEntity.ok().body(userService.loginUser(userLoginRequest));
	}

	@PostMapping("/sign-up")
	public ResponseEntity<UserIdResponse> signUpUser(@Valid @RequestBody UserSignUpRequest userSignUpRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUpUser(userSignUpRequest));
	}

	@GetMapping
	public ResponseEntity<UserDetailsResponse> retrieveUser(@RequestHeader("Token") String token) {
		return ResponseEntity.ok().body(userService.retrieveUser(token));
	}

	@PutMapping
	public ResponseEntity<UserDetailsResponse> updateUser(@RequestHeader("Token") String token, @Valid @RequestBody
	UserUpdateRequest userUpdateRequest) {
		return ResponseEntity.ok().body(userService.updateUser(token, userUpdateRequest));
	}

	@DeleteMapping
	public ResponseEntity<UserDeleteResponse> deleteUser(@RequestHeader("Token") String token,
		@RequestHeader("password") String password) {
		return ResponseEntity.ok().body(userService.deleteUser(token, password));
	}
}
