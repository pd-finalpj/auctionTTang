package com.encore.auction.controller.address.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.auction.controller.address.responses.AddressDetailsListResponse;
import com.encore.auction.service.address.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	private final AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping
	public ResponseEntity<AddressDetailsListResponse> getAddressList() {
		return ResponseEntity.ok().body(addressService.getAddressList());
	}
}
