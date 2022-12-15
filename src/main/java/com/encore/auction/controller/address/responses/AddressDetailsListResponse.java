package com.encore.auction.controller.address.responses;

import java.util.List;

import lombok.Getter;

@Getter
public class AddressDetailsListResponse {

	private final List<AddressDetailsResponse> addressDetailsResponses;

	public AddressDetailsListResponse(List<AddressDetailsResponse> addressDetailsResponses) {
		this.addressDetailsResponses = addressDetailsResponses;
	}
}
