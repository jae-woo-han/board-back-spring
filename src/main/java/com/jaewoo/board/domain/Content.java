package com.jaewoo.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;

public class Content {
/*
 * 댓글
 * id(인덱스),
 * postId(속한 게시글 번호)
 * content(내용),
 * user(작성한 유저),
 * date정보(작성, 수정)
 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "content_id")
	private Long id;
	private String content;
	private Post post;
	private User user;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	@Builder
	public Content(String content, Post post, User user, LocalDateTime createDate, LocalDateTime updateDate) {
		this.content = content;
		this.post = post;
		this.user = user;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
}
