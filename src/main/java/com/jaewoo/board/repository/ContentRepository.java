package com.jaewoo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaewoo.board.domain.User;

public interface ContentRepository extends JpaRepository<User, Long>{

}
