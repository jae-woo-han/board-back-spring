package com.jaewoo.board.controller;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jaewoo.board.domain.User;
import com.jaewoo.board.dto.LoginRequest;
import com.jaewoo.board.dto.LoginResponse;
import com.jaewoo.board.dto.SignupFormDto;
import com.jaewoo.board.repository.UserRepository;
import com.jaewoo.board.service.AuthService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final AuthService authService;
	private final UserRepository userRepository;

	@GetMapping("/signup")
	public String moveSignupPage(Model model) {
		model.addAttribute("form", new SignupFormDto());
		return "auth/signup.html";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute("form") SignupFormDto form) {
		authService.signup(User.builder().username(form.getUsername()).password(form.getPassword())
				.nickname(form.getNickname()).build());

		return "redirect:/";
	}

	@GetMapping("/login")
	public String moveLoginPage(Model model) {
		model.addAttribute("form", new LoginRequest());
		return "auth/login.html";
	}

	// form 형식으로 인증 시 쓰는 메서드
	@PostMapping("/authenticate")
	public String authorize(@ModelAttribute("form") LoginRequest form, Model model, HttpServletResponse response) {
		// 아이디와 비밀번호 확인(로그인 절차)
		User user = userRepository
				.findOneByUsernameAndPassword(form.getUsername(), form.getPassword())
				.orElseThrow();
		// 토큰생성, 토큰 쿠키에 추가
		String token = authService.getUserWithToken(form.getUsername());
		Cookie jwtCookie = new Cookie("Authorization", token);
		jwtCookie.setHttpOnly(true);
		jwtCookie.setPath("/");
		response.addCookie(jwtCookie);
		// model에 응답할 유저정보 추가
		LoginResponse responseDto = new LoginResponse();
		responseDto.setUsername(user.getUsername());
		responseDto.setNickname(user.getNickname());
		model.addAttribute("user", responseDto);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		
		Cookie jwtCookie = new Cookie("Authorization", null);
		response.addCookie(jwtCookie);
		
		return "redirect:/";		
	}
}
