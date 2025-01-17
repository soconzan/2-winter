package com.example.myschool.lecture;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myschool.lecture.service.LectureService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lectures")
public class LectureController {
	
	private final LectureService lectureService;
	
	/*
	 * private List<LectureDto> lectures = new ArrayList<LectureDto>();
	 * 
	 * public LectureController() { lectures.add(new LectureDto(1, 1, "Java",
	 * "Java language Basic Course")); lectures.add(new LectureDto(2, 2,
	 * "Spring boot", "Spring Boot with java")); lectures.add(new LectureDto(3, 1,
	 * "Kotlin", "Kotlin language Basic Course")); lectures.add(new LectureDto(4, 2,
	 * "Spring boot(kotlin)", "Spring Boot with kotlin")); lectures.add(new
	 * LectureDto(5, 3, "Node.js", "Node.js + express")); }
	 */

	@GetMapping("")
	public List<LectureDto> getLectures(
			@RequestParam(name = "title", required = false) String title) {
		
		/*
		 * if (title == null) return lectures; return lectures.stream() .filter(l ->
		 * l.getTitle().toUpperCase().contains(title.toUpperCase())).collect(Collectors.
		 * toList());
		 */
		
		return lectureService.getLectures(title);

	}

	@GetMapping("/{id}")
	public LectureDto getLecture(
			@PathVariable("id") int id) {
		
		/*
		 * return lectures.stream() .filter(l -> l.getLectureId() ==
		 * id).findFirst().orElse(null);
		 */
		
		return lectureService.getLecture(id);

	}
}