package com.jaewoo.board.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostPageResponse {
	Long id;
	String title;
	String writer;
	LocalDateTime postDate;
	
}
