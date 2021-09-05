package com.jaewoo.board.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jaewoo.board.domain.Comment;
import com.jaewoo.board.domain.Post;
import com.jaewoo.board.domain.User;
import com.jaewoo.board.dto.CommentFormDto;
import com.jaewoo.board.repository.CommentRepository;
import com.jaewoo.board.repository.PostRepository;
import com.jaewoo.board.repository.UserRepository;

@Controller
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/new")
	public String saveNewComment(@ModelAttribute("form") CommentFormDto form) {
		
		Optional<User> user = userRepository.findOneByUsername(form.getWriter());
		Optional<Post> post = postRepository.findById(form.getPostId());
		
		Comment comment = Comment.builder()
					.content(form.getContent())
					.post(post.orElseThrow())
					.user(user.orElseThrow())
					.createDate(LocalDateTime.now())
					.updateDate(LocalDateTime.now())
					.build();
		
		commentRepository.save(comment);
		
		return "redirect:/";
	}
}
