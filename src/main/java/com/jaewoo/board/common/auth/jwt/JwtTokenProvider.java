package com.jaewoo.board.common.auth.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	private String secretKey;
	private long validityInMilliseconds;
	private Key key;
	
	public JwtTokenProvider(String secretKey, long validityInMilliseconds) {
		this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
		this.validityInMilliseconds = validityInMilliseconds;
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public String createToken(String subject) {
		Claims claims = Jwts.claims().setSubject(subject);
		
		long now = (new Date()).getTime();
		Date expirationTime = new Date(now+ this.validityInMilliseconds);
		
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(expirationTime)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	
	
}
