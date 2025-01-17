package com.example.myschool.teacher.domain;

import java.util.List;

import com.example.myschool.lecture.domain.Lecture;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Entity 클래스
 */
@Getter
@Setter
@Entity
@NoArgsConstructor // 모든 파라미터 생성 안 해도 됨
@Builder
@AllArgsConstructor // 모두 생성해도 됨
public class Teacher {
	
	@Id // PK를 의미
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// IDENTITY : auto increment -> id 입력할 때 null 값 주면 됨
	private Integer teacherId; // JPA에서는 int 대신 Integer 사용
	
	@Column(length = 50)
	private String name;
	
	@Column(length = 200)
	private String description;
	
	// 선생님이 담당하는 수업 목록도 보고 싶을 때
	@OneToMany(mappedBy="teacher")
	private List<Lecture> lectures;
	
}
