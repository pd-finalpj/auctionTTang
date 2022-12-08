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
import com.encore.auction.service.manager.ManagerService;

@RestController
@RequestMapping("/manager")
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
	public ResponseEntity<ManagerIdResponse> loginManager(@RequestBody ManagerLoginRequest managerLoginRequest) {
		return ResponseEntity.ok().body(managerService.loginManager(managerLoginRequest));
	}

	@PostMapping("/sign-up")
	public ResponseEntity<ManagerIdResponse> signUpManager(@RequestBody ManagerSignUpRequest managerSignUpRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(managerService.signUpManager(managerSignUpRequest));
	}

	@GetMapping("/{manager-id}")
	public ResponseEntity<ManagerDetailsResponse> retrieveManager(@PathVariable("manager-id") String managerId) {
		return ResponseEntity.ok().body(managerService.retrieveManager(managerId));
	}

	@PutMapping("/{manager-id}")
	public ResponseEntity<ManagerDetailsResponse> updateManager(@PathVariable("manager-id") String managerId,
		@Valid @RequestBody
		ManagerUpdateRequest managerUpdateRequest) {
		return ResponseEntity.ok().body(managerService.updateManager(managerId, managerUpdateRequest));
	}

	@DeleteMapping("/{manager-id}")
	public ResponseEntity<ManagerDeleteResponse> deleteManager(@PathVariable("manager-id") String managerId,
		@RequestHeader("password") String password) {
		return ResponseEntity.ok().body(managerService.deleteManager(managerId, password));
	}
}
