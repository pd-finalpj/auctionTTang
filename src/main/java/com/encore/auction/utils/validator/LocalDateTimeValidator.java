package com.encore.auction.utils.validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.encore.auction.exception.WrongTimeException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateTimeValidator {

	private static LocalDateTimeValidator localDateTimeValidator = null;

	public static LocalDateTimeValidator of() {
		if (localDateTimeValidator == null) {
			localDateTimeValidator = new LocalDateTimeValidator();
		}
		return localDateTimeValidator;
	}

	public String convertLocalDateTimeToString(LocalDateTime localDateTime) {
		return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public LocalDateTime convertStringToLocalDateTime(String dateTime) {
		LocalDateTime localDateTime;
		try {
			localDateTime = LocalDateTime.parse(dateTime,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			throw new WrongTimeException("Wrong time request : " + dateTime);
		}
		return localDateTime;
	}
}
