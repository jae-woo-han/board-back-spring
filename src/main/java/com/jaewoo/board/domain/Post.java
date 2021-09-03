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
public class Post {
/*
 * 게시글
 * id(인덱스),
 * title,
 * content(내용),
 * user(작성한 유저, user_id),
 * date정보(작성, 수정)
 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;
	private String title;
	@Column(length = 5000)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
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
	
	//수정 관련 메서드
	//제목 변경
	public void changeTitle(String title) {
		this.title = title;
		setUpdateTime();
	}
	//내용 변경
	public void changeContent(String content) {
		this.content = content;
		setUpdateTime();
	}
	//수정일 변경
	private void setUpdateTime() {
		this.updateDate = LocalDateTime.now();
	}
}
