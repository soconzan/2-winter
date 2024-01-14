package com.example.myschool.post.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class PostDto {
	
	private int postId;
	private String title;
	private String content;
	private int pw;
	private Date date;
}
