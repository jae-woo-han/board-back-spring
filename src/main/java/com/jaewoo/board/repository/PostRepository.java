package com.jaewoo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaewoo.board.domain.User;

public interface PostRepository extends JpaRepository<User, Long>{

}
