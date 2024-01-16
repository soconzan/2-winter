package com.example.memos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.memos.domain.Memo;
import com.example.memos.domain.User;
import com.example.memos.dto.AddMemoRequest;
import com.example.memos.dto.GetMemoResponse;
import com.example.memos.repository.MemoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemoService {

	private final MemoRepository memoRepository;
	
	// 메모 조회 2
	private GetMemoResponse entityToResponse(Memo memo) {
		return new GetMemoResponse(
				memo.getId(),
				memo.getBody(),
				memo.getCreatedAt(),
				memo.getUser().getUsername()
				);
	}
	
	// 메모 등록
	private Memo requestToEntity(AddMemoRequest dto) {
		User user = new User();
		user.setUsername(dto.getUserName());
		
		return new Memo(
				null,
				dto.getBody(),
				null,
				user
				);
	}
	
	// 메모 조회 1
	public List<GetMemoResponse> getMemoByUser(String username) {
		return memoRepository.findByUser_usernameOrderByCreatedAtDesc(username).stream()
				.map(memo -> entityToResponse(memo))
				.collect(Collectors.toList());
	}
	
	// 메모 등록
	public Long addMemo(AddMemoRequest dto) {
		Memo result = memoRepository.save(requestToEntity(dto));
		return result.getId();
	}
	
	// 메모 삭제
	public void deleteMemo(Long id) {
		memoRepository.deleteById(id);
	}

}
