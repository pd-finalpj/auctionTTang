package com.encore.auction.exception;

public class WrongRequestException extends SecurityException {
	public WrongRequestException(String message) {
		super(message);
	}
}
