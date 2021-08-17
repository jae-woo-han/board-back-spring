package com.jaewoo.board.common.auth.jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
	private String secretKey;
	private long validInMilliseconds;
	
	public JwtTokenProvider(String secretKey, long validInMilliseconds) {
		this.secretKey = secretKey;
		this.validInMilliseconds = validInMilliseconds;
	}
	
	
	
}
