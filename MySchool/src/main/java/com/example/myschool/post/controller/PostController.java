package com.example.myschool.post.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myschool.post.domain.Post;
import com.example.myschool.post.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
	
	private final PostService postService;
	
	@GetMapping("")
	public List<Post> viewPosts(){
		return postService.getPosts();
	}
	
	@GetMapping("/{id}")
	public Post viewPost(Model model, @PathVariable("id") int id) {
		return postService.getPost(id);
	}
}
