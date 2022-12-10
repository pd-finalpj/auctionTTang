package com.encore.auction.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.encore.auction.exception.WrongRequestException;
import com.encore.auction.utils.token.JwtProvider;

@Component
public class BearerAuthInterceptor implements HandlerInterceptor {

	private JwtProvider jwtProvider;

	public BearerAuthInterceptor(JwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler) {

		String token = request.getHeader("Token");

		if (token == null) {
			throw new WrongRequestException("Token does not exist");
		} else if (token.isEmpty()) {
			throw new WrongRequestException("Token is Empty");
		}

		if (!jwtProvider.validateToken(token)) {
			throw new IllegalArgumentException("유효하지 않은 토큰");
		}

		return true;
	}
}
