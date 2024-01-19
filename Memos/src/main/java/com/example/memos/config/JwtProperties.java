package com.example.memos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt")
public class JwtProperties { // application.properties 파일 내용을 코드에서 사용할 수 있도록 클래스 작성
	
	private String issuer;
	private String secretKey;
	private int duration;
	private int refreshDuration;
}
