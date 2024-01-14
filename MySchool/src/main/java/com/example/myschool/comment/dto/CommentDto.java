package com.example.myschool.comment.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CommentDto {
	
	private int cmntId;
	private String content;
	private int pw;
	private Date date;
	private int postId;
}
