package com.jaewoo.board.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jaewoo.board.domain.User;

@SpringBootTest
@Transactional
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
	void 저장() {
		User user = User
					.builder()
					.username("한재우")
					.password("123456")
					.nickname("째우")
					.build();
		 userRepository.save(user);
	}
	@Test
	void 조회() {
		User findUser = userRepository.findById(1L).get();
		
		assertTrue(findUser.getNickname().equals("째우"));
	}
	@Test
	void 이름으로조회() {
		User findUser = userRepository.findOneByUsername("한재우").get();
		
		assertTrue(findUser.getNickname().equals("째우"));
	}

}
