package com.loanapp.loanManagementSystem.service;

import com.loanapp.loanManagementSystem.dto.user.CourseDto;
import com.loanapp.loanManagementSystem.entities.user.CourseDetails;
import com.loanapp.loanManagementSystem.entities.user.User;
import com.loanapp.loanManagementSystem.mapper.user.CourseMapper;
import com.loanapp.loanManagementSystem.repository.CourseRepository;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import com.loanapp.loanManagementSystem.service.user.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
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
    @DisplayName("Throw exception when user id not found while fetching course details")
    void testGet_UserIdNotFound(){
        UUID random= UUID.randomUUID();
        when(courseRepository.findByUserId(random)).thenReturn(Optional.empty());

        RuntimeException exception=assertThrows(RuntimeException.class,()->courseService.getCourseById(random));
        assertEquals("User not found",exception.getMessage());
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


@Test
@DisplayName("Throw exception when user id not found")
    void testSave_UserNotFound(){
    UUID random= UUID.randomUUID();
    when(userRepository.findById(random)).thenReturn(Optional.empty());

    RuntimeException exception=assertThrows(RuntimeException.class,()->courseService.saveCourseDetails(random,new CourseDto()));
    assertEquals("User ID does not exist",exception.getMessage());
}

@Test
    @DisplayName("Get all course details for existing user")
    void testGetallCourseDetails(){
        CourseDetails details=mock(CourseDetails.class);
        CourseDto dto=mock(CourseDto.class);

        when(courseRepository.findAll()).thenReturn(List.of(details));
        when(mapper.toDto(details)).thenReturn(dto);

        List<CourseDto> result=courseService.getAllCourse();

        assertNotNull(result);
        assertEquals(1,result.size());

}

@Test
    @DisplayName("Throw exception when user id not found while updating ")

    void testUpdate_UserIdNotFound(){
    UUID random= UUID.randomUUID();
    when(courseRepository.findByUserId(random)).thenReturn(Optional.empty());

    RuntimeException exception=assertThrows(RuntimeException.class,()->courseService.updateCourseDetails(random,new CourseDto()));
    assertEquals("Course details not found",exception.getMessage());
}

}
