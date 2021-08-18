package com.jaewoo.board.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jaewoo.board.domain.Comment;
import com.jaewoo.board.domain.Post;
import com.jaewoo.board.domain.User;

@SpringBootTest
@Transactional
class CommentRepositoryTest {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	CommentRepository commentRepository;
	
	User user;
	Post post;
	
	//유저와 게시글 생성
	@BeforeEach
	void 사전생성() {
		User user = User
				.builder()
				.username("한재우")
				.password("123456")
				.nickname("째우")
				.build();
		this.user = userRepository.save(user);
		Post post = Post.builder()
				.user(user)
				.title("제목")
				.content("내용")
				.createDate(LocalDateTime.now())
				.updateDate(null)
				.build();
		this.post = postRepository.save(post);
	}
	
	@Test
	void 댓글등록() {
		Comment comment = Comment.builder()
					.content("댓댓구")
					.post(post)
					.user(user)
					.createDate(LocalDateTime.now())
					.build();
		Comment savedComment = commentRepository.save(comment);
		assertSame(comment, savedComment, "객체불일치");
	}
	@Test
	void 댓글수정() {
		Comment comment = Comment.builder()
				.content("댓댓구")
				.post(post)
				.user(user)
				.createDate(LocalDateTime.now())
				.build();
		Comment savedComment = commentRepository.save(comment);
		savedComment.changeContent("댓댓구리");
		commentRepository.save(savedComment);
		assertTrue(savedComment.getContent().equals("댓댓구리"));
	}

}
