package com.encore.auction.utils.security;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.encore.auction.exception.WrongRequestException;
import com.encore.auction.utils.token.JwtProvider;

@Aspect
@Component
public class LoginCheckAspect {

	private final JwtProvider jwtProvider;
	private final HttpServletRequest httpServletRequest;

	public LoginCheckAspect(JwtProvider jwtProvider, HttpServletRequest httpServletRequest) {
		this.jwtProvider = jwtProvider;
		this.httpServletRequest = httpServletRequest;
	}

	@Around("@annotation(Permission)")
	public Object accessToken(final ProceedingJoinPoint pjp) throws Throwable {
		String token = httpServletRequest.getHeader("Token");

		if (token == null) {
			throw new WrongRequestException("Token does not exist");
		} else if (token.isEmpty()) {
			throw new WrongRequestException("Token is Empty");
		}

		if (!jwtProvider.validateToken(token)) {
			throw new IllegalArgumentException("유효하지 않은 토큰");
		}

		return pjp.proceed();
	}
}
