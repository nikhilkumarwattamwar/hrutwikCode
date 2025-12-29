package com.loanapp.loanManagementSystem.service;

import com.loanapp.loanManagementSystem.dto.user.EducationDto;
import com.loanapp.loanManagementSystem.entities.user.EducationDetails;
import com.loanapp.loanManagementSystem.entities.user.User;
import com.loanapp.loanManagementSystem.mapper.user.EducationMapper;
import com.loanapp.loanManagementSystem.repository.EducationRepository;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import com.loanapp.loanManagementSystem.service.user.EducationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EducationDetailsServiceTest {
    @InjectMocks
    EducationServiceImpl educationService;

    @Mock
    EducationRepository educationRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    EducationMapper mapper;

    UUID id;
    EducationDto dto;
    List<EducationDto> dtoList;
    EducationDetails entity;
    List<EducationDetails> entityList;
    User user;


    @BeforeEach
    void setUp(){
        user= new User();
        id=UUID.randomUUID();

        dto= new EducationDto();
        dtoList=List.of(dto);

        entity = new EducationDetails();
        entityList=List.of(entity);
    }

    @Test
    @DisplayName("saveEducationDetail : adding all education details for existing user id ")
    void testSaveEducationDetail(){
        when(userRepository.findById(id)).thenReturn(Optional.ofNullable(user));
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(educationRepository.saveAll(any())).thenReturn(entityList);
        when(mapper.toDtoList(entityList)).thenReturn(dtoList);

        List<EducationDto> result=educationService.saveEducationDetail(dtoList,id);

        assertNotNull(result);
        assertEquals(dtoList,result);

        verify(userRepository).findById(id);
        verify(educationRepository).saveAll(any());
        verify(mapper).toDtoList(entityList);
    }

    @Test
    @DisplayName("GetAllEducationDetail: Fetch all education details")
    void testGetAllEducationDetail() {

        when(educationRepository.findAll()).thenReturn(entityList);
        when(mapper.toDto(entity)).thenReturn(dto);

        List<EducationDto> result = educationService.getAllEducationDetail();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(educationRepository).findAll();
        verify(mapper).toDto(entity);
    }

    @Test
    @DisplayName(" getEducationDetailById:  Fetch education details by user ID")
    void testGetEducationDetailById() {

        when(educationRepository.findAllByUserId(id)).thenReturn(Optional.of(entityList));
        when(mapper.toDtoList(entityList)).thenReturn(dtoList);

        List<EducationDto> result = educationService.getEducationDetailById(id);

        assertNotNull(result);
        assertEquals(dtoList, result);

        verify(educationRepository).findAllByUserId(id);
        verify(mapper).toDtoList(entityList);
    }

    @Test
    @DisplayName("updateEducationDetail : Update existing education details for user")
    void testUpdateEducationDetail() {

        user.setEducationDetailsList(Arrays.asList(entity));

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(educationRepository.findByUserIdAndExamPassed(eq(id),any())).thenReturn(Optional.of(entity));

        List<EducationDto> result =
                educationService.updateEducationDetail(dtoList, id);

        assertNotNull(result);

        verify(userRepository).findById(id);
        verify(mapper).updateFromDtoToEntity(dto, entity);
        verify(userRepository).save(user);
    }

}
