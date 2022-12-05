package com.encore.auction.controller.user.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.user.requests.UserLoginRequest;
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
}
