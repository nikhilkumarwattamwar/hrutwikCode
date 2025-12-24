package com.loanapp.loanManagementSystem.service;

import com.loanapp.loanManagementSystem.dto.CourseDto;
import com.loanapp.loanManagementSystem.entities.CourseDetails;
import com.loanapp.loanManagementSystem.entities.User;
import com.loanapp.loanManagementSystem.mapper.CourseMapper;
import com.loanapp.loanManagementSystem.repository.CourseRepository;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@ExtendWith(MockitoExtension.class)
public class TestCourseService {
    @Mock
    CourseMapper mapper;

    @Mock
    CourseRepository courseRepository;

    @InjectMocks
    CourseServiceImpl courseService;

    @Mock
    UserRepository userRepository;

   public CourseDto courseDto;
    CourseDetails detailsEntity;
    User user;
    UUID userID;

    @BeforeEach
    void setUp(){
         userID= UUID.randomUUID();
        courseDto= new CourseDto();
        detailsEntity= new CourseDetails();
        user= new User();
    }


    @Test
    @DisplayName("saveCourseDetails :")
    void testsaveCourseDetails(){

        when(userRepository.findById(userID)).thenReturn(Optional.ofNullable(user));
        when(mapper.toEntity(courseDto)).thenReturn(detailsEntity);
        when(courseRepository.save(detailsEntity)).thenReturn(detailsEntity);
        when(mapper.toDto(detailsEntity)).thenReturn(courseDto);

        CourseDto result=courseService.saveCourseDetails(userID,courseDto);

        assertNotNull(result);
        verify(userRepository).findById(userID);
        verify(courseRepository).save(detailsEntity);

    }

    @Test
    @DisplayName("getCourseById : Fetch course details by user ID")
    void testGetCourseById(){

        when(courseRepository.findByUserId(userID)).thenReturn(Optional.ofNullable(detailsEntity));
        when(mapper.toDto(detailsEntity)).thenReturn(courseDto);

        CourseDto result=courseService.getCourseById(userID);

        assertNotNull(result);
        verify(courseRepository).findByUserId(userID);

    }

    @Test
    @DisplayName("updateCourseDetails : updating course details for existing user")
    void testUpdateCourseDetails(){

        when(courseRepository.findByUserId(userID)).thenReturn(Optional.ofNullable(detailsEntity));
        when(courseRepository.save(detailsEntity)).thenReturn(detailsEntity);
        when(mapper.toDto(detailsEntity)).thenReturn(courseDto);

        CourseDto result=courseService.updateCourseDetails(userID,courseDto);

        assertNotNull(result);
        verify(courseRepository).findByUserId(userID);
    }



}
