package com.encore.auction.controller.manager.api;

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

import com.encore.auction.controller.manager.requests.ManagerLoginRequest;
import com.encore.auction.controller.manager.requests.ManagerSignUpRequest;
import com.encore.auction.controller.manager.requests.ManagerUpdateRequest;
import com.encore.auction.controller.manager.responses.ManagerDeleteResponse;
import com.encore.auction.controller.manager.responses.ManagerDetailsResponse;
import com.encore.auction.controller.manager.responses.ManagerIdCheckResponse;
import com.encore.auction.controller.manager.responses.ManagerIdResponse;
import com.encore.auction.controller.manager.responses.ManagerTokenResponse;
import com.encore.auction.service.manager.ManagerService;
import com.encore.auction.utils.security.Permission;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

	private final ManagerService managerService;

	public ManagerController(ManagerService managerService) {
		this.managerService = managerService;
	}

	@GetMapping("/check-id-exist/{manager-id}")
	public ResponseEntity<ManagerIdCheckResponse> checkManagerIdExist(@PathVariable("manager-id") String managerId) {
		return ResponseEntity.ok().body(managerService.checkManagerIdExist(managerId));
	}

	@PostMapping("/login")
	public ResponseEntity<ManagerTokenResponse> loginManager(
		@Valid @RequestBody ManagerLoginRequest managerLoginRequest) {
		return ResponseEntity.ok().body(managerService.loginManager(managerLoginRequest));
	}

	@PostMapping("/sign-up")
	public ResponseEntity<ManagerIdResponse> signUpManager(
		@Valid @RequestBody ManagerSignUpRequest managerSignUpRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(managerService.signUpManager(managerSignUpRequest));
	}

	@Permission
	@GetMapping
	public ResponseEntity<ManagerDetailsResponse> retrieveManager(@RequestHeader("Token") String token) {
		return ResponseEntity.ok().body(managerService.retrieveManager(token));
	}

	@Permission
	@PutMapping
	public ResponseEntity<ManagerDetailsResponse> updateManager(@RequestHeader("Token") String token,
		@Valid @RequestBody
		ManagerUpdateRequest managerUpdateRequest) {
		return ResponseEntity.ok().body(managerService.updateManager(token, managerUpdateRequest));
	}

	@Permission
	@DeleteMapping
	public ResponseEntity<ManagerDeleteResponse> deleteManager(@RequestHeader("Token") String token,
		@RequestHeader("password") String password) {
		return ResponseEntity.ok().body(managerService.deleteManager(token, password));
	}
}
