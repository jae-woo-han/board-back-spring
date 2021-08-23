package com.jaewoo.board.dto;

import lombok.Data;

@Data
public class SignupFormDto {
	String username;
	String password;
	String passwordConfirm;
	String nickname;
}
