package com.jaewoo.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jaewoo.board.dto.PostFormDto;


@Controller
public class PostController {

	@GetMapping("/posts/new")
	public String moveNewPostPage(Model model) {
		model.addAttribute("form", new PostFormDto());
		return "post/createPostForm.html";
	}
	
	@PostMapping("/posts/new")
	public String saveNewPost(@ModelAttribute("form") PostFormDto form) {
		System.out.println(form.toString());
		
		return "redirect:/";
	}
	
}
