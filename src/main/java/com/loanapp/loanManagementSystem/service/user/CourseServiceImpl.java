package com.loanapp.loanManagementSystem.service.user;

import com.loanapp.loanManagementSystem.dto.user.CourseDto;
import com.loanapp.loanManagementSystem.entities.user.CourseDetails;
import com.loanapp.loanManagementSystem.entities.user.User;
import com.loanapp.loanManagementSystem.mapper.user.CourseMapper;
import com.loanapp.loanManagementSystem.repository.CourseRepository;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseMapper mapper;

    public CourseDto saveCourseDetails(UUID userId, CourseDto dto) {

        log.info("Saving course details for userId: {}", userId);

        User user = userRepository.findById(userId).orElseThrow(() -> {
            log.error("User not found with userId: {}", userId);
            return new RuntimeException("User ID does not exist");
        });


        CourseDetails details = mapper.toEntity(dto);

        user.setCourseDetails(details);

        details.setUser(user);

        CourseDetails saved = courseRepository.save(details);

        log.info("Course details saved successfully for userId: {}", userId);

        return mapper.toDto(saved);
    }

    public CourseDto getCourseById(UUID userId) {


        log.info("Fetching course details for userId: {}", userId);

        CourseDetails details = courseRepository.findByUserId(userId).orElseThrow(() -> {
            log.error("Course details not found for userId: {}", userId);
            return new RuntimeException("User not found");
        });

        return mapper.toDto(details);
    }

    public List<CourseDto> getAllCourse() {

        log.info("Fetching all course details");

        List<CourseDetails> details = courseRepository.findAll();

        return details.stream().map(mapper::toDto).toList();
    }

    public CourseDto updateCourseDetails(UUID userId, CourseDto dto) {

        log.info("Updating course details for userId: {}", userId);

        CourseDetails existingDetails = courseRepository.findByUserId(userId).orElseThrow(() -> {
            log.error("Course details not found with userId: {}", userId);
            return new RuntimeException("Course details not found");
        });
        mapper.updateFromDtoToEntity(dto, existingDetails);

        CourseDetails updatedDetails = courseRepository.save(existingDetails);

        log.info("Course details updated successfully for userId: {}", userId);


        return mapper.toDto(updatedDetails);

    }


}
