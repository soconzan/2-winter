package com.example.myschool.comment.domain;

import java.util.Date;

import com.example.myschool.post.domain.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cmntId;

	@Column(length = 300, nullable = false)
	private String content;

	@Column(nullable = false)
	private Integer pw;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne(fetch = FetchType.EAGER) // 순환 참조..
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
}
