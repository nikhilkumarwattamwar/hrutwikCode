package com.loanapp.loanManagementSystem.contollers.user;

import com.loanapp.loanManagementSystem.dto.user.CourseDto;
import com.loanapp.loanManagementSystem.service.user.CourseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user/course")
public class CourseDetailController {

    @Autowired
    CourseService service;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{userId}")
    public CourseDto addCourseDetails(@RequestBody @Valid CourseDto dto,
                                      @PathVariable UUID userId) {
        CourseDto savedDto = service.saveCourseDetails(userId, dto);
        return savedDto;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public CourseDto getCourseDetailById(@PathVariable UUID userId) {
        CourseDto courseDto = service.getCourseById(userId);
        return courseDto;
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{userId}")
    public CourseDto updateCourseDetail(@RequestBody @Valid CourseDto dto,
                                        @PathVariable UUID userId) {
        CourseDto updatedDto = service.updateCourseDetails(userId, dto);
        return updatedDto;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteCourse(@PathVariable UUID userId) {
        service.deleteCourseByUserId(userId);
        return ResponseEntity.ok("Course details deleted successfully");
    }

}
