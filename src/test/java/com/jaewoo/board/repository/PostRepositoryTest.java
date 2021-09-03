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
					.content("Manual testing is not what it was five years ago.\r\n"
							+ "\r\n"
							+ "The number of pure manual testing jobs is low and when such a job is available, the competition is fierce. It is common these days to meet manual testers that have a difficult time finding a new job.\r\n"
							+ "\r\n"
							+ "Due to budget restrictions for the testing team, there are also cases where manual testers are let go, but test automation engineers retained.\r\n"
							+ "\r\n"
							+ "Which doesn't make sense since developers are not great testers.\r\n"
							+ "On the other hand, it is easier for developers to learn testing than it is for testers to learn development.\r\n"
							+ "\r\n"
							+ "Considering the market situation, many manual testers are now becoming more interested in test automation.\r\n"
							+ "\r\n"
							+ "Some want to just expand their skills by including programming and a test automation framework in their testing toolbox. Others are contemplating switching the direction completely and focusing on automation only.\r\n"
							+ "\r\n"
							+ "But how can you find that next automation job when you do not know programming?")
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
