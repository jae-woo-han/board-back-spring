package com.jaewoo.board.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import lombok.Data;

/*
 * 등록 된 게시글 내부
 * 글번호, 제목, 내용, 글쓴이, 등록일, 댓글리스트
 */
@Data
public class PostDto {
	Long id;
	String title;
	String content;
	String writer;
	LocalDateTime postDate;
	Page<CommentDto> commentDtoPage;
}
