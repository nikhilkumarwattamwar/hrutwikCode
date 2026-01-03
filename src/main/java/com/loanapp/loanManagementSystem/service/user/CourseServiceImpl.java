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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseMapper mapper;

    @Transactional
    public CourseDto saveCourseDetails(UUID userId, CourseDto dto) {

        User user = userRepository.findById(userId).orElseThrow(() -> {
            return new RuntimeException("User ID does not exist");
        });


        CourseDetails details = mapper.toEntity(dto);

        user.setCourseDetails(details);

        details.setUser(user);

        CourseDetails saved = courseRepository.save(details);

        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public CourseDto getCourseById(UUID userId) {

        CourseDetails details = courseRepository.findByUserId(userId).orElseThrow(() -> {
            return new RuntimeException("User not found");
        });

        return mapper.toDto(details);
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getAllCourse() {

        List<CourseDetails> details = courseRepository.findAll();

        return details.stream().map(mapper::toDto).toList();
    }

    @Transactional
    public CourseDto updateCourseDetails(UUID userId, CourseDto dto) {

        CourseDetails existingDetails = courseRepository.findByUserId(userId).orElseThrow(() -> {
            return new RuntimeException("Course details not found");
        });
        mapper.updateFromDtoToEntity(dto, existingDetails);

        CourseDetails updatedDetails = courseRepository.save(existingDetails);


        return mapper.toDto(updatedDetails);

    }

    @Transactional
    @Override
    public void deleteCourseByUserId(UUID userId) {

        CourseDetails details = courseRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Course details not found"));

        courseRepository.delete(details);
    }

}
