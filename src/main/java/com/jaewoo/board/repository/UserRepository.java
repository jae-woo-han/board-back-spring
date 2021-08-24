package com.jaewoo.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaewoo.board.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
/* 필요기능
 * 회원가입, 로그인, 조회, 전체조회
 */
	Optional<User> findOneByUsername(String username);
	Optional<User> findOneByUsernameAndPassword(String username, String password);
}
