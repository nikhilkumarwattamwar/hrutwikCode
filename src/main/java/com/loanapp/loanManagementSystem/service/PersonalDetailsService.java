package com.loanapp.loanManagementSystem.service;

import com.loanapp.loanManagementSystem.dto.PersonalDto;

import java.util.UUID;

public interface PersonalDetailsService {
    PersonalDto saveDetailsById(PersonalDto dto, UUID applicantID);
    public PersonalDto getDetailsByID(UUID id);
    PersonalDto updateDetails(PersonalDto dto,UUID id);
}
