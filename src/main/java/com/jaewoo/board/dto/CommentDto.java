package com.jaewoo.board.dto;

import lombok.Data;

/*
 * 등록된 댓글 기본 형식
 * 글쓴이, 내용, 등록일
 */
@Data
public class CommentDto {
	Long id;
	String content;
	String writer;
	String commentDate;
}
