package com.jaewoo.board.controller.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaewoo.board.domain.User;
import com.jaewoo.board.dto.SignupRequest;
import com.jaewoo.board.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<User> signup(@Valid @RequestBody SignupRequest signupRequest) {
		return ResponseEntity.ok(authService.signup(User.builder().username(signupRequest.getUsername())
				.password(signupRequest.getPassword()).nickname(signupRequest.getNickname()).build()));
	}
 
}
