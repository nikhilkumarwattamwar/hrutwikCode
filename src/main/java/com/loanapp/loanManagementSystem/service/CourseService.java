package com.loanapp.loanManagementSystem.service;

import com.loanapp.loanManagementSystem.dto.CourseDto;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    CourseDto saveCourseDetails(UUID userId, CourseDto dto);
    CourseDto getCourseById(UUID userId);
    List<CourseDto> getAllCourse();
    CourseDto updateCourseDetails(UUID userId,CourseDto dto);
}
