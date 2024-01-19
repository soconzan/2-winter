package com.example.memos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninResponse {
	private String token;
	private String refreshToken;
	private int status;
}
