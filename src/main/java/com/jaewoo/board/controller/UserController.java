package com.jaewoo.board.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jaewoo.board.domain.User;
import com.jaewoo.board.dto.SignupFormDto;
import com.jaewoo.board.service.AuthService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final AuthService authService;
	
	@GetMapping("/signup")
	public String moveSignupPage(Model model) {
		model.addAttribute("form", new SignupFormDto());
		return "auth/signup.html";
	}
	
	@PostMapping("/signup")
	public String signup(
			@ModelAttribute("form") SignupFormDto form){
		authService.signup(
				User.builder()
				.username(form.getUsername())
				.password(form.getPassword())
				.nickname(form.getNickname())
				.build()
				);
		
		return "redirect:/";
	}
}
