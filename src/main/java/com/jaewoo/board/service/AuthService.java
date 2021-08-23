package com.jaewoo.board.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaewoo.board.common.auth.jwt.JwtTokenProvider;
import com.jaewoo.board.domain.User;
import com.jaewoo.board.dto.SignupRequest;
import com.jaewoo.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;

	@Transactional
	public User signup(User signupRequest) {

		if (userRepository.findOneByUsername(signupRequest.getUsername()).isPresent()) {
			throw new IllegalArgumentException("아이디 중복");
		}
		User user = User.builder()
				.username(signupRequest.getUsername())
				.password(signupRequest.getPassword())
				.nickname(signupRequest.getNickname())
				.createDate(LocalDateTime.now())
				.updateDate(LocalDateTime.now())
				.build();
		return userRepository.save(user);
	}
	
	public String getUserWithToken(String username){
		userRepository.findOneByUsername(username).orElseThrow();
		return jwtTokenProvider.createToken(username);
	}
}
