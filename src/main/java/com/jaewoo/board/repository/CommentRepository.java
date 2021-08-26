package com.jaewoo.board.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jaewoo.board.domain.Comment;
import com.jaewoo.board.domain.Post;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	Page<Comment> findByPost(Post post, Pageable pageable);
}
