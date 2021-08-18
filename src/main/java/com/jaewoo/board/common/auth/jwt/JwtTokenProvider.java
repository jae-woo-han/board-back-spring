package com.jaewoo.board.common.auth.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider implements InitializingBean {
	
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(JwtTokenProvider.class);
	private String secretKey;
	private long tokenValidityInMilliseconds;
	private Key key;
	
	public JwtTokenProvider(
			@Value("${jwt.secret}") String secretKey, 
			@Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds
			) {
		this.secretKey = secretKey;
		this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		byte[] secretKeyBytes =secretKey.getBytes(StandardCharsets.UTF_8);
		this.key = Keys.hmacShaKeyFor(secretKeyBytes);
	}
	
	public String createToken(String subject) {
		Claims claims = Jwts.claims().setSubject(subject);
		
		long now = (new Date()).getTime();
		Date expirationTime = new Date(now+ this.tokenValidityInMilliseconds);
		
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(expirationTime)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}

	public String getSubject(String token) {
		return Jwts.parserBuilder().setSigningKey(secretKey).build()
				.parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build()
				.parseClaimsJws(token);
			return true;
		} catch (JwtException e) {//세부 예외 설정 필요
			logger.info("JWT 예외 발생");
			return false;
		}
	}
	
	
	
}
