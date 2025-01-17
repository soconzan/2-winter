package com.example.myschool.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.myschool.lecture.service.LectureService;
import com.example.myschool.teacher.domain.Teacher;
import com.example.myschool.teacher.service.TeacherService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WebController {
	
	private final TeacherService teacherService;
	private final LectureService lectureService;

	@GetMapping("/")
	public String index(Model model) {
		
		Teacher teacher = Teacher.builder()
				.teacherId(1)
				.name("JavaKim")
				.description("No.1 Java Teacher")
				.build();
		
		model.addAttribute("username", "hello");
		model.addAttribute("teacher", teacher);
		return "index";
	}

	@GetMapping("/view-teachers")
	public String viewTeachers(Model model) {
		model.addAttribute("teachers", teacherService.getTeachers(null));
		return "teachers";
	}
	
	@GetMapping("/view-lectures")
	public String viewLectures(Model model) {
		model.addAttribute("lectures", lectureService.getLectures(null));
		return "lectures";
	}
	
	@GetMapping("/teacher/{id}")
	public String viewTeacher(Model model, @PathVariable("id") int id) {
		model.addAttribute("teacher", teacherService.getTeacher(id));
		return "teacher";
	}

	@GetMapping("/lecture/{id}")
	public String viewLecture(Model model, @PathVariable("id") int id) {
		model.addAttribute("lecture", lectureService.getLecture(id));
		return "lecture";
	}
	
}
