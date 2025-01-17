package com.example.myschool.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder // lombok에서 제공
public class TeacherDto {
	private int teacherId;
	private String name;
	private String description;
}