package com.jaewoo.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaewoo.board.domain.User;

@RestController
public class AuthController {

	
	@PostMapping("/signup")
	public ResponseEntity<User> signup(){
		
		return ResponseEntity.ok(User.builder().build());
	}
	
	
}
