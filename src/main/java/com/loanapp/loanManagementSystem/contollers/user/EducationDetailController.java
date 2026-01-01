package com.loanapp.loanManagementSystem.contollers.user;

import com.loanapp.loanManagementSystem.dto.user.EducationDto;
import com.loanapp.loanManagementSystem.service.user.EducationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user/education")
public class EducationDetailController {

    @Autowired
    EducationService service;

    @PostMapping("/{userId}")
    public List<EducationDto> addEducationDetails(@RequestBody @Valid List<EducationDto> dto,
                                                  @PathVariable UUID userId) {
        List<EducationDto> savedList = service.saveEducationDetail(dto, userId);
        return savedList;
    }

    @GetMapping("/{userId}")
    public List<EducationDto> getEducationDetailById(@PathVariable UUID userId) {
        List<EducationDto> educationList = service.getEducationDetailById(userId);
        return educationList;
    }

    @PutMapping("/{userId}")
    public List<EducationDto> updateEducationDetails(@RequestBody @Valid List<EducationDto> dto,
                                                     @PathVariable UUID userId) {
        List<EducationDto> updatedList = service.updateEducationDetail(dto, userId);
        return updatedList;
    }

}
