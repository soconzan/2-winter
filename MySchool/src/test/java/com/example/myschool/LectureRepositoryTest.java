package com.example.myschool;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.myschool.lecture.domain.Lecture;
import com.example.myschool.lecture.repository.LectureRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LectureRepositoryTest {
	
	@Autowired
	LectureRepository repository;
	
	@Test
	void findAll() {
		List<Lecture> list = repository.findAll();
		Assertions.assertEquals(list.size(), 3);
	}
}
