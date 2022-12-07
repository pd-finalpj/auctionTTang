package com.encore.auction.exception;

public class WrongTimeException extends RuntimeException {
	public WrongTimeException(String message) {
		super(message + "잘못된 시간 형식 입니다.");
	}
}
