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
import com.encore.auction.controller.user.requests.UserSiginUpRequest;
import com.encore.auction.controller.user.requests.UserUpdateRequest;
import com.encore.auction.controller.user.responses.UserDeleteResponse;
import com.encore.auction.controller.user.responses.UserDetailsResponse;
import com.encore.auction.controller.user.responses.UserIdResponse;
import com.encore.auction.service.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<UserIdResponse> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
		return ResponseEntity.ok().body(userService.loginUser(userLoginRequest));
	}

	@PostMapping("/sign-up")
	public ResponseEntity<UserIdResponse> signUpUser(@Valid @RequestBody UserSiginUpRequest userSiginUpRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUpUser(userSiginUpRequest));
	}

	@GetMapping("/{user-id}")
	public ResponseEntity<UserDetailsResponse> retrieveUser(@PathVariable("user-id") String userId) {
		return ResponseEntity.ok().body(userService.retrieveUser(userId));
	}

	@PutMapping("/{user-id}")
	public ResponseEntity<UserDetailsResponse> updateUser(@PathVariable("user-id") String userId, @RequestBody
	UserUpdateRequest userUpdateRequest) {
		return ResponseEntity.ok().body(userService.updateUser(userId, userUpdateRequest));
	}

	@DeleteMapping("/{user-id}")
	public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable("user-id") String userId,
		@RequestHeader("password") String password) {
		return ResponseEntity.ok().body(userService.deleteUser(userId, password));
	}
}
