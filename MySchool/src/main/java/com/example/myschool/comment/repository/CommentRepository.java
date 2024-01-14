package com.example.myschool.comment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myschool.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	public Comment save(Comment comment);
	public List<Comment> findByPost_PostIdOrderByDateDesc(int postId);
	public Optional<Comment> findById(int id);
	public void deleteById(int id);

}
