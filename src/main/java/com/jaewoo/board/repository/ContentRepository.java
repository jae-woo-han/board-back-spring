package com.jaewoo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaewoo.board.domain.Content;

public interface ContentRepository extends JpaRepository<Content, Long>{

}
