package com.example.myschool.comment.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myschool.comment.domain.Comment;
import com.example.myschool.comment.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comments")
public class CommentController {
	
	private final CommentService commentService;
	
	// 게시글 모든 댓글 조회
	@GetMapping("/{postId}")
	public List<Comment> viewComments(@PathVariable("postId") int postId){
		return commentService.getComments(postId);
	}
	
	// 댓글 조회
	@GetMapping("/select/{id}")
	public Comment viewComment(@PathVariable("id") int id) {
		return commentService.getComment(id);
	}
	
	// 댓글 삭제 및 게시글 댓글 조회
	@GetMapping("/delete/{id}")
	public List<Comment> deleteComment(@PathVariable("id") int id) {
		int postId = commentService.getComment(id).getPost().getPostId();
		commentService.deleteComment(id);
		return commentService.getComments(postId);
	}
	
}
