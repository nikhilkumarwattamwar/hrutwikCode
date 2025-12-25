package com.loanapp.loanManagementSystem.controller;

import com.loanapp.loanManagementSystem.contollers.user.EducationDetailController;
import com.loanapp.loanManagementSystem.dto.user.EducationDto;
import com.loanapp.loanManagementSystem.service.user.EducationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEducationDetailsController {
    @InjectMocks
    EducationDetailController controller;

    @Mock
    EducationService service;

    UUID userID;
    EducationDto dto;
    List<EducationDto> dtoList;

    @BeforeEach
     void setUp(){
        userID = UUID.randomUUID();
        dto= new EducationDto();
        dtoList= Arrays.asList(dto);

    }

    @Test
    @DisplayName("Add education details for valid user ID")
    void testAddEducationDetails(){
        when(service.saveEducationDetail(dtoList,userID)).thenReturn(dtoList);

        List<EducationDto> result=controller.addEducationDetails(dtoList,userID);

        assertEquals(result,dtoList);
        verify(service,times(1)).saveEducationDetail(dtoList,userID);
    }

    @Test
    @DisplayName("Fetch education details by user ID")
    void testGetEducationDetailById() {

        when(service.getEducationDetailById(userID)).thenReturn(dtoList);

        List<EducationDto> result = controller.getEducationDetailById(userID);

        assertEquals(dtoList, result);
        verify(service, times(1)).getEducationDetailById(userID);
    }

    @Test
    @DisplayName("Update education details for valid user ID")
    void testUpdateEducationDetails() {

        when(service.updateEducationDetail(dtoList, userID)).thenReturn(dtoList);

        List<EducationDto> result = controller.updateEducationDetails(dtoList, userID);

        assertEquals(dtoList, result);
        verify(service, times(1)).updateEducationDetail(dtoList, userID);
    }





}
