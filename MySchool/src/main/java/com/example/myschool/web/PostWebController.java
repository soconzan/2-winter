package com.example.myschool.web;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.myschool.comment.controller.CommentForm;
import com.example.myschool.comment.domain.Comment;
import com.example.myschool.comment.service.CommentService;
import com.example.myschool.post.controller.PostForm;
import com.example.myschool.post.domain.Post;
import com.example.myschool.post.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostWebController {
	
	private final PostService postService;
	private final CommentService commentService;
	
	// 게시판 view
	@GetMapping("/posts")
	public String viewPosts(Model model){
		model.addAttribute("posts", postService.getPosts());
		return "posts";
	}
	
	// 게시글 view + 댓글
	@GetMapping("/posts/{id}")
	public String viewPost(Model model, @PathVariable("id") int id) {
		model.addAttribute("post", postService.getPost(id));
		model.addAttribute("comments", commentService.getComments(id));
		return "post";
	}
	
	// 게시글 작성 view
	@GetMapping("/posts/write")
	public String writePost(Model model) {
		model.addAttribute("post", new Post());
		return "write";
	}
	
	// 게시글 등록 -> 게시글 목록 view
	@PostMapping("/posts/create")
	public String createPost(PostForm form) {
		Post post = new Post();
		
		post.setTitle(form.getTitle());
		post.setContent(form.getContent());
		post.setPw(form.getPw());
		post.setDate(new Date());
		
		postService.save(post);
		
		return "redirect:/posts";
	}
	
	// 게시글 삭제
	@GetMapping("/posts/delete/{id}")
	public String deletePost(@PathVariable("id") int id) {
		postService.deletePost(id);
		return "postDelete";
	}
	
	// 게시글 수정 view
	@GetMapping("posts/write/{id}")
	public String editPost(Model model, @PathVariable("id") int id) {
		model.addAttribute("post", postService.getPost(id));
		return "write";
	}
	
	// 게시글 업데이트 -> 게시글 view
	@PostMapping("/posts/update/{id}")
	public String updatePost(@PathVariable("id") int id, PostForm form) {
		postService.updatePost(id, form);
		return "redirect:/posts/{id}";
	}
	
	// 댓글 등록
	@PostMapping("/comment/create/{id}")
	public String createCmnt(@PathVariable("id") int postId, CommentForm form) {
		Comment comment = new Comment();
		
		comment.setContent(form.getContent());
		comment.setPw(form.getPw());
		comment.setDate(new Date());
		comment.setPost(postService.getPost(postId));
		
		commentService.save(comment);
		
		return "redirect:/posts/{id}";
	}
	
	// 댓글 삭제
	@GetMapping("/comment/delete/{id}")
	public String deleteCmnt(@PathVariable("id") int id) {
		return "redirect:/posts/{id}";
	}
}
