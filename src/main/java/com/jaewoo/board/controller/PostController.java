package com.jaewoo.board.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jaewoo.board.domain.Post;
import com.jaewoo.board.domain.User;
import com.jaewoo.board.dto.PostFormDto;
import com.jaewoo.board.repository.PostRepository;
import com.jaewoo.board.repository.UserRepository;


@Controller
public class PostController {
	
	//간단한 기능은 repository로 바로처리
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserRepository userRepository;

	@GetMapping("/posts/new")
	public String moveNewPostPage(Model model) {
		model.addAttribute("form", new PostFormDto());
		return "post/createPostForm.html";
	}
	
	/*
	 * 게시글 쓰면
	 * 제목, 내용, 유저정보, 작성/수정일 필요
	 */
	@PostMapping("/posts/new")
	public String saveNewPost(@ModelAttribute("form") PostFormDto form) {
		//유저정보는 jwt를 이용할 거니까
		//form에서 같이 보내지는 username을 통해서 조회
		Optional<User> user = userRepository.findOneByUsername(form.getUsername());
		
		Post post = Post.builder()
				.title(form.getTitle())
				.content(form.getContent())
				.user(user.orElseThrow())
				.createDate(LocalDateTime.now())
				.updateDate(LocalDateTime.now())
				.build();
		
		//저장 되고나서 저장한 게시글 주소로 이동하도록 짤 것
		postRepository.save(post);
		return "redirect:/";
	}
	
}
