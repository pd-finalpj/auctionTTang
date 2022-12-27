package com.encore.auction.service.address;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.encore.auction.controller.address.responses.AddressDetailsListResponse;
import com.encore.auction.controller.address.responses.AddressDetailsResponse;
import com.encore.auction.model.address.Address;
import com.encore.auction.repository.address.AddressRepository;

@Service
public class AddressService {

	private final AddressRepository addressRepository;

	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	public AddressDetailsListResponse getAddressList() {
		List<Address> addressList = addressRepository.findAll();

		List<AddressDetailsResponse> addressDetailsResponses = addressList.stream()
			.map(e -> new AddressDetailsResponse(e.getAddressCode(), e.getStateName(), e.getCityName()))
			.collect(
				Collectors.toList());

		return new AddressDetailsListResponse(addressDetailsResponses);
	}
}
