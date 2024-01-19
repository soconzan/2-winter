package com.example.memos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccessTokenRequest {
	
	private String username;
	private String password;
	private String refreshToken;
	
}
