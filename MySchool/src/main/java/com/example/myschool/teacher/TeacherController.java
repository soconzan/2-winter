package com.example.myschool.teacher;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myschool.teacher.service.TeacherService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 미리 만들어 놓은 생성자 연결
@RestController
@RequestMapping("/teachers")
public class TeacherController {

	private final TeacherService teacherService;

	// DB 연결 후 삭제
	/*
	 * private List<TeacherDto> teachers = new ArrayList<TeacherDto>();
	 * 
	 * public TeacherController() { teachers.add(new TeacherDto(1, "AAA",
	 * "Java/Kotlin Language Teacher")); teachers.add(new TeacherDto(2, "BBB",
	 * "Spring framework Teacher")); teachers.add(new TeacherDto(3, "CCC",
	 * "Node.js Teacher")); };
	 */

	@GetMapping("")
	public List<TeacherDto> getTeachers(

			@RequestParam(name = "name", required = false) String name) {
		/*
		 * if (name == null) return teachers; return teachers.stream().filter(t ->
		 * t.getName().toUpperCase().contains(name.toUpperCase()))
		 * .collect(Collectors.toList());
		 */
		
		// DB 연결 후 코드 변경
		return teacherService.getTeachers(name);

	}

	@GetMapping("/{id}")
	public TeacherDto getTeacher(

			@PathVariable("id") int id) {
		/*
		 * return teachers.stream().filter(t -> t.getTeacherId() ==
		 * id).findFirst().orElse(null);
		 */
		
		return teacherService.getTeacher(id);

	}
}