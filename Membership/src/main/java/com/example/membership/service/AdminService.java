package com.example.membership.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.membership.domain.Admin;
import com.example.membership.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService {
	
	private final AdminRepository adminRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private Admin requestToEntity(Admin admin) {
		String pw = bCryptPasswordEncoder.encode(admin.getPw());
		return new Admin(admin.getAdminId(), pw);
	}
	
	// 회원가입
	public Admin join(Admin admin) {
//		Admin result = adminRepository.save(requestToEntity(admin));
		return adminRepository.save(requestToEntity(admin));
	}
	
	public Admin getAdmin(String id) {
		return adminRepository.findById(id).get();
	}
}
