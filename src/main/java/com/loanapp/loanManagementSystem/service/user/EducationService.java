package com.loanapp.loanManagementSystem.service.user;

import com.loanapp.loanManagementSystem.dto.user.EducationDto;

import java.util.List;
import java.util.UUID;

public interface EducationService {

    List<EducationDto> saveEducationDetail(List<EducationDto> dto, UUID userId);

    List<EducationDto> getAllEducationDetail();

    List<EducationDto> getEducationDetailById(UUID userID);

    List<EducationDto> updateEducationDetail(List<EducationDto> dto, UUID userId);
}
