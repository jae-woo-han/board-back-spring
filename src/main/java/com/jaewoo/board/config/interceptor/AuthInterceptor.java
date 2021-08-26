package com.jaewoo.board.config.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jaewoo.board.common.auth.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
	private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies = request.getCookies();
		if(cookies == null) {
			response.sendRedirect("/login");
			return false;
		}
		String jwtCookie = "";
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("Authorization")) {
				jwtCookie = cookie.getValue();
			}
		}
		if(jwtCookie.isEmpty()) {
			return true;
		}
		if(!jwtTokenProvider.validateToken(jwtCookie)) {
			throw new IllegalArgumentException("유효하지 않은 토큰");
		}
		String username = jwtTokenProvider.getSubject(jwtCookie);
		request.setAttribute("username", username);
		return true;
	}
}
