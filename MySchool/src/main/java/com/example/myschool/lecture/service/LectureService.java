package com.example.myschool.lecture.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.myschool.lecture.LectureDto;
import com.example.myschool.lecture.domain.Lecture;
import com.example.myschool.lecture.repository.LectureRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LectureService {

	private final LectureRepository lectureRepository;

	// 강의 목록 검색
	public List<LectureDto> getLectures(String title) {

		ArrayList<LectureDto> lectures = new ArrayList<LectureDto>();

		if (title == null) {
			lectureRepository.findAll().forEach(t -> {

				LectureDto lectureDto = LectureDto.builder().description(t.getDescription()).title(t.getTitle())
						.lectureId(t.getLectureId()).teacherId(t.getTeacher().getTeacherId()).build();

				lectures.add(lectureDto);
			});
		} else {
			lectureRepository.findByTitle(title).forEach(t -> {

				LectureDto lectureDto = LectureDto.builder().description(t.getDescription()).title(t.getTitle())
						.lectureId(t.getLectureId()).teacherId(t.getTeacher().getTeacherId()).build();

				lectures.add(lectureDto);
			});
		}

		return lectures;
	}

	// 강의 한 건 조회
	public LectureDto getLecture(int id) {
		Optional<Lecture> result = lectureRepository.findById(id);

		if (result.isPresent()) {
			Lecture l = result.get();
			return new LectureDto(l.getLectureId(), l.getTeacher().getTeacherId(), l.getTitle(), l.getDescription());
		} else {
			return null;
		}
	}
}
