package com.example.myschool;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.myschool.teacher.domain.Teacher;
import com.example.myschool.teacher.repository.TeacherRepository;

//모든 서버 X, DB만 테스트
@DataJpaTest
// Test DB를 따로 쓸 것인지 여부, NONE : 기존 DB 사용
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeacherRepositoryTest {

	// repository 필요 -> 의존성 주입
	@Autowired
	TeacherRepository repository;

	// teacher 레코드 개수
	@Test
	void findAll() {
		List<Teacher> list = repository.findAll();
		Assertions.assertEquals(list.size(), 2);
	}
}
