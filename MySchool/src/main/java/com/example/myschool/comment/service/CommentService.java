package com.example.myschool.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.myschool.comment.domain.Comment;
import com.example.myschool.comment.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	
	private final CommentRepository commentRepository;
	
	// 댓글 등록
	public Comment save(Comment comment) {
		commentRepository.save(comment);
		return comment;
	}
	
	// 글의 모든 댓글 조회
	public List<Comment> getComments(int postId) {
		return commentRepository.findByPost_PostIdOrderByDateDesc(postId);
	}
	
	// 댓글 조회
	public Comment getComment(int id) {
		return commentRepository.findById(id).get();
	}
	
	// 댓글 삭제
	public void deleteComment(int id) {
		commentRepository.deleteById(id);
	}

}
