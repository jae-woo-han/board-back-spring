package com.jaewoo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaewoo.board.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
