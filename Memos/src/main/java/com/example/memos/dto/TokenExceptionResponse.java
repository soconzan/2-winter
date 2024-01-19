package com.example.memos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenExceptionResponse { // 만료&비정상 토큰일 때 보낼 Dto
	private String result;
}
