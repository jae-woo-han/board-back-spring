package com.jaewoo.board.dto;

import lombok.Data;

@Data
public class CommentFormDto {
	String content;
	String writer;
	Long postId;
}
