package com.example.myschool.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myschool.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	public List<Post> findAll();
	public List<Post> findAllByOrderByDateDesc();
	public Optional<Post> findById(int id);
	public Post save(Post post);
	public void deleteById(int id);
}
