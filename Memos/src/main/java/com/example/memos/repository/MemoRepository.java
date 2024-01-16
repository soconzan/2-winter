package com.example.memos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.memos.domain.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {
	
	/*
	 * select *
	 * from memos
	 * where username = ''
	 * order by created_at desc;
	 */
	public List<Memo> findByUser_usernameOrderByCreatedAtDesc(String username);

}
