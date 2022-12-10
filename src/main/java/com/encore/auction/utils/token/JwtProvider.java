package com.encore.auction.utils.token;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	private String secretKey;

	private long expirationTime;

	public JwtProvider(@Value("${jwt.secret-key}") String secretKey,
		@Value("${jwt.expiration-time}") long expirationTime) {
		this.secretKey = secretKey;
		this.expirationTime = expirationTime;
	}

	public String createToken(String id, String userOrManager) {
		Claims claims = Jwts.claims().setSubject(id).setAudience(userOrManager);

		Date now = new Date();
		Date validity = new Date(now.getTime() + expirationTime);

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(validity)
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	public String getSubject(String token) {
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

	public String getAudience(String token) {
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody()
			.getAudience();
	}

	public boolean validateToken(String token) {
		Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		return !claims.getBody().getExpiration().before(new Date());
	}

}
