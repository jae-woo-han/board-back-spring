package com.jaewoo.board.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jaewoo.board.domain.Post;
import com.jaewoo.board.dto.PostPageResponse;
import com.jaewoo.board.repository.PostRepository;

@Controller
public class HomeController {
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/")
	public String home(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page) {
		PageRequest pageRequest = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "id"));
		Page<Post> posts = postRepository.findAll(pageRequest);
		Page<PostPageResponse> postPage = posts.map(m -> {
			PostPageResponse res = new PostPageResponse();
			res.setId(m.getId());
			res.setTitle(m.getTitle());
			res.setPostDate(m.getCreateDate());
			res.setWriter(m.getUser().getNickname());
			return res;
		});
		model.addAttribute("posts", postPage);
		
		return "/home.html";
	}
}
