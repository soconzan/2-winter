package com.example.memos.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.memos.domain.User;
import com.example.memos.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final UserRepository userRepository;

	@Override
	/*
	 * exception 발생 ->
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> result = userRepository.findById(username);
		
		if (result.isEmpty())
			throw new UsernameNotFoundException("Invalid username");
		
		User user = result.get();
		// builder 형식
		// 여기서 User는 domain User가 아닌 userdetails User를 반환
		return org.springframework.security.core.userdetails.User
				.withUsername(username)
				.password(user.getPassword())
				.roles(user.getRole()) // spring authority 사용해도 됨
				.build();
	}

}
