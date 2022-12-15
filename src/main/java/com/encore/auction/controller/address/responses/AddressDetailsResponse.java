package com.encore.auction.controller.address.responses;

import lombok.Getter;

@Getter
public final class AddressDetailsResponse {

	private final String addressCode;

	private final String stateName;

	private final String cityName;

	public AddressDetailsResponse(String addressCode, String stateName, String cityName) {
		this.addressCode = addressCode;
		this.stateName = stateName;
		this.cityName = cityName;
	}
}
