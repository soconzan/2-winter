package com.example.memos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.memos.domain.User;

public interface UserRepository extends JpaRepository<User, String>{
	
}
