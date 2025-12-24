package com.loanapp.loanManagementSystem.controller;

import com.loanapp.loanManagementSystem.contollers.CourseDetailController;
import com.loanapp.loanManagementSystem.dto.CourseDto;
import com.loanapp.loanManagementSystem.repository.CourseRepository;
import com.loanapp.loanManagementSystem.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestCourseController {

    @InjectMocks
    CourseDetailController controller;

    @Mock
    CourseService service;

    @Mock
    CourseRepository repository;

    UUID userId;
    CourseDto dto;

    @BeforeEach
    void setUp(){
        userId= UUID.randomUUID();
        dto= new CourseDto();
    }

    @Test
    @DisplayName("addCourseDetails : Adding course details for the existing user")
    void testAddCourseDetails(){

        when(service.saveCourseDetails(userId,dto)).thenReturn(dto);

        CourseDto result=controller.addCourseDetails(dto,userId);

        assertEquals(result,dto);
        verify(service,times(1)).saveCourseDetails(userId,dto);

    }

    @Test
    @DisplayName("GetCourseDetailById : fetching course details using user id")
    void testGetCourseDetailById(){
        when(service.getCourseById(userId)).thenReturn(dto);

        CourseDto result=controller.getCourseDetailById(userId);

        assertEquals(result,dto);
        verify(service,times(1)).getCourseById(userId);
    }

    @Test
    @DisplayName("updateCourseDetail : updating course detail for existing user")
    void testUpdateCourseDetails(){
        when(service.updateCourseDetails(userId,dto)).thenReturn(dto);

        CourseDto result=controller.updateCourseDetail(dto,userId);

        assertNotNull(result);
        verify(service,times(1)).updateCourseDetails(userId,dto);
    }

}
