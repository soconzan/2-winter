package com.example.memos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse { // 파일 정보 반환
	
	private String contentType; // 파일 타입 (이미지, 비디오, 문서 등)
	private byte[] bytes;
}
