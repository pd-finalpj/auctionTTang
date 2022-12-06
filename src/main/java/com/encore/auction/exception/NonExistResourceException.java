package com.encore.auction.exception;

public class NonExistResourceException extends NullPointerException {
	public NonExistResourceException(String message) {
		super(message);
	}
}
