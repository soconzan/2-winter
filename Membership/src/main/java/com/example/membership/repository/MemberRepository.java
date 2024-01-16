package com.example.membership.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.membership.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	
	public List<Member> findAllByOrderByPhone();
}
