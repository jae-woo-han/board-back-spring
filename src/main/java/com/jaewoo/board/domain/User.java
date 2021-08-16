package com.jaewoo.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;

public class User {
/* 게시판 사용할 유저 도메인
 * id(인덱스), 
 * username(아이디), 
 * password(비밀번호), 
 * nickname(사이트에서 사용할 닉네임)
 * date정보(작성, 수정)
 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	private String username;
	private String password;
	private String nickname;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	@Builder
	public User(String username, String password, String nickname, LocalDateTime createDate, LocalDateTime updateDate) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
}
