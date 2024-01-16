package com.example.membership.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.membership.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{
	
}
