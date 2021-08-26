package com.jaewoo.board.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jaewoo.board.domain.Post;
import com.jaewoo.board.repository.PostRepository;

@Controller
public class HomeController {
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		PageRequest pageRequest = PageRequest.of(0, 20);
		Page<Post> posts = postRepository.findAll(pageRequest);
		model.addAttribute("posts", posts);
		
		return "/home.html";
	}
}
