package com.example.myschool.lecture.domain;

import com.example.myschool.teacher.domain.Teacher;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Lecture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer lectureId;

	@Column(length = 50)
	private String title;

	@Column(length = 200)
	private String description;

	// 일대다 관계 설정, '다'에 설정 : FK
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.REMOVE) // many - 나임, one - 상대방임
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
}
