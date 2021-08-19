package com.jaewoo.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Data;

@Controller
public class PostController {

	@GetMapping("/posts/new")
	public String moveNewPostPage(Model model) {
		model.addAttribute("form", new PostForm());
		return "post/createPostForm.html";
	}
	
	@Data
	class PostForm{
		String title;
		String content;
	}
}
