package com.jaewoo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaewoo.board.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
/* 필요기능
 * 회원가입, 로그인, 조회, 전체조회
 */
}
