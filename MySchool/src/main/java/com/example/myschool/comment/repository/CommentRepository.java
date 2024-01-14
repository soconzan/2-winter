package com.example.myschool.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myschool.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	public Comment save(Comment comment);
	public List<Comment> findByPost_PostIdOrderByDateDesc(int postId);

}
