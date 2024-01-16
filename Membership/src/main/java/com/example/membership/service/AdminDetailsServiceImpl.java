package com.example.membership.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.membership.domain.Admin;
import com.example.membership.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminDetailsServiceImpl implements UserDetailsService {
	
	private final AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Admin> result = adminRepository.findById(username);
		
		if (result.isEmpty())
			throw new UsernameNotFoundException("Invalid adminId");
		
		Admin admin = result.get();
		
		return org.springframework.security.core.userdetails.User
				.withUsername(username)
				.password(admin.getPw())
				.build();
	}

}
