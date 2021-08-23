package com.jaewoo.board.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jaewoo.board.domain.User;
import com.jaewoo.board.dto.SignupRequest;
import com.jaewoo.board.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/api/signup")
	public ResponseEntity<User> signup(@Valid @RequestBody SignupRequest signupRequest) {
		return ResponseEntity.ok(authService.signup(User.builder().username(signupRequest.getUsername())
				.password(signupRequest.getPassword()).nickname(signupRequest.getNickname()).build()));
	}

}
