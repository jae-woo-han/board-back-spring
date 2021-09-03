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
