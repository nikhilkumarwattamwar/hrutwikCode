package com.loanapp.loanManagementSystem.service;

import com.loanapp.loanManagementSystem.dto.EducationDto;

import java.util.List;
import java.util.UUID;

public interface EducationService {

    List<EducationDto> saveEducationDetail(List<EducationDto> dto, UUID userId);
    List<EducationDto> getAllEducationDetail();
    List<EducationDto> getEducationDetailById(UUID userID);
    List<EducationDto> updateEducationDetail(List<EducationDto> dto, UUID userId);
}
