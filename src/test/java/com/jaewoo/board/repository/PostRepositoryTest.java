package com.jaewoo.board.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jaewoo.board.domain.Post;
import com.jaewoo.board.domain.User;

@SpringBootTest
@Transactional
class PostRepositoryTest {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PostRepository postRepository;
	
	User user;
	
	@BeforeEach
	void 유저정보등록() {
		User user = User
				.builder()
				.username("한재우")
				.password("123456")
				.nickname("째우")
				.build();
		this.user = userRepository.save(user);
	}
	
	@Test
	void 저장() {
		Post post = Post.builder()
					.user(user)
					.title("제목")
					.content("내용")
					.createDate(LocalDateTime.now())
					.updateDate(null)
					.build();
		Post savedPost = postRepository.save(post);
		assertSame(post, savedPost, "객체불일치");
	}
	@Test
	void 조회() {
		Post post = Post.builder()
				.user(user)
				.title("제목")
				.content("내용")
				.createDate(LocalDateTime.now())
				.updateDate(null)
				.build();
		postRepository.save(post);
		
		Post findPost = postRepository.findAll().get(0);
		assertEquals(user, findPost.getUser());
		assertTrue(post.getTitle().equals((findPost).getTitle()));
	}
	@Test
	void 수정() {
		Post post = Post.builder()
				.user(user)
				.title("제목")
				.content("내용")
				.createDate(LocalDateTime.now())
				.updateDate(null)
				.build();
		postRepository.save(post);
		
		Post findPost = postRepository.findAll().get(0);
		
		findPost.changeContent("바뀐내용");
		postRepository.save(findPost);
		Post findPost2 = postRepository.findAll().get(0);
		
		assertSame(findPost, findPost2, "객체불일치");
		
		assertTrue(findPost.getContent().equals("바뀐내용"));
	}

}
