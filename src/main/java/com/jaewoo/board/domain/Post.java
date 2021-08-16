package com.jaewoo.board.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;

public class Post {
/*
 * 게시글
 * id(인덱스),
 * title,
 * content(내용),
 * user(작성한 유저),
 * date정보(작성, 수정)
 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;
	private String title;
	private String content;
	private User user;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	@Builder
	public Post(String title, String content, User user, LocalDateTime createDate, LocalDateTime updateDate) {
		this.title = title;
		this.content = content;
		this.user = user;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
}
