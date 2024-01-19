package com.example.memos.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.memos.domain.User;
import com.example.memos.dto.AddUserRequest;
import com.example.memos.dto.AddUserResponse;
import com.example.memos.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bcryptPasswordEncoder;

	private User requestToEntity(AddUserRequest dto) {
		String password = bcryptPasswordEncoder.encode(dto.getPassword()); // 비밀번호 암호화
		return new User(dto.getUsername(), password, "user");
	}

	private AddUserResponse entityToResponse(User user) {
		return new AddUserResponse(user.getUsername(), "ok");
	}
	
	// DB에 User 추가
	public AddUserResponse addUser(AddUserRequest dto) {
		User result = userRepository.save(requestToEntity(dto));
		return entityToResponse(result);
	}
	
	public User findById(String id) throws IllegalArgumentException{
		return userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user"));
	}

}
