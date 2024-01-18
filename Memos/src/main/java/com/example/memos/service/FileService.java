package com.example.memos.service;

import java.io.File;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.memos.dto.FileResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileService {

	@Value("${file.dir}") // 저장할 폴더 경로
	private String fileDir;

	// 파일 저장
	public String uploadFile(MultipartFile file) {
		
		if (!file.isEmpty()) {
			String uuid = UUID.randomUUID().toString();
			String savedName = uuid + "_" + file.getOriginalFilename(); // uuid를 사용하여 저장명 생성
			File dest = new File(fileDir, savedName);
			
			try {
				file.transferTo(dest); // 지정한 폴더 경로에 새로운 저장명으로 파일 전송
				return savedName;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	// 파일 불러오기
	public FileResponse getFile(String name) {
		FileResponse res = new FileResponse();
		
		try {
			File file = new File(fileDir, name); // name으로 폴더 내에서 파일 조회
			res.setBytes(FileCopyUtils.copyToByteArray(file)); // 파일의 byte 준비
			res.setContentType(Files.probeContentType(file.toPath())); // 파일 type과 함께 반환
		} catch (Exception e) {
			
		}
		return res;
	}

}
