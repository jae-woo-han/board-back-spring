package com.jaewoo.board.common.auth.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider implements InitializingBean {
	
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(JwtTokenProvider.class);
	private String secretKey;
	private long tokenValidityInMilliseconds;
	private Key key;
	
	public JwtTokenProvider(String secretKey, long tokenValidityInMilliseconds) {
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

	
	
	
}
