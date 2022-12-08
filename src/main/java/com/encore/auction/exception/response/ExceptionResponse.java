package com.encore.auction.exception.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class ExceptionResponse {

	private final String code;
	private final String message;

	public ExceptionResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
