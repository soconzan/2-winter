package com.example.membership.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.membership.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	
	public List<Member> findAllByOrderByPhone();
	
	// contain 사용하면 일부 번호만 검색해도 뜸
//	public List<Member> findByIdContain
}
