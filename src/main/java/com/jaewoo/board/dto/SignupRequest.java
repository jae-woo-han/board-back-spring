package com.jaewoo.board.dto;

import lombok.Data;

//username, password, nickname
@Data
public class SignupRequest {
	private String username;
	private String password;
	private String nickname;
}
