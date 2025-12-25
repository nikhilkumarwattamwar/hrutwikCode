package com.loanapp.loanManagementSystem.contollers.user;

import com.loanapp.loanManagementSystem.dto.user.CourseDto;
import com.loanapp.loanManagementSystem.service.user.CourseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user/course")
public class CourseDetailController {

    private static final Logger log = LoggerFactory.getLogger(CourseDetailController.class);

    @Autowired
    CourseService service;

    @PostMapping("/{userId}")
    public CourseDto addCourseDetails(@RequestBody @Valid CourseDto dto, @PathVariable UUID userId){

        log.info("Adding course details for userId: {}", userId);

        CourseDto savedDto = service.saveCourseDetails(userId, dto);

        log.info("Course details added successfully for userId: {}", userId);
        return savedDto;
    }


    @GetMapping("/{userId}")
   public CourseDto getCourseDetailById(@PathVariable UUID userId){

        log.info("Fetching course details for userId: {}", userId);

        CourseDto courseDto = service.getCourseById(userId);

        log.info("Course details fetched successfully for userId: {}", userId);
        return courseDto;
    }

    @PutMapping("/{userId}")
   public CourseDto updateCourseDetail(@RequestBody @Valid CourseDto dto, @PathVariable UUID userId){
        log.info("Updating course details for userId: {}", userId);

        CourseDto updatedDto = service.updateCourseDetails(userId, dto);

        log.info("Course details updated successfully for userId: {}", userId);
        return updatedDto;
    }

}
