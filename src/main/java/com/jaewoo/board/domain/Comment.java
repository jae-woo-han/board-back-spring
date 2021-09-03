package com.jaewoo.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {
/*
 * 댓글
 * id(인덱스),
 * postId(속한 게시글 번호, post_id)
 * content(내용),
 * user(작성한 유저,user_id),
 * date정보(작성, 수정)
 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "content_id")
	private Long id;
	@Column(length = 2000)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	
	@Builder
	public Comment(String content, Post post, User user, LocalDateTime createDate, LocalDateTime updateDate) {
		this.content = content;
		this.post = post;
		this.user = user;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	
	//수정 관련 메서드
	//내용변경
	public void changeContent(String content) {
		this.content = content;
		setUpdateTime();
	}
	//수정일 갱신
	private void setUpdateTime() {
		this.updateDate = LocalDateTime.now();
	}
}
