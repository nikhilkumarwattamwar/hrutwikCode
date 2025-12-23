package com.loanapp.loanManagementSystem.contollers;

import com.loanapp.loanManagementSystem.dto.EducationDto;
import com.loanapp.loanManagementSystem.service.EducationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user/education")
public class EducationDetailController {
    private static final Logger log = LoggerFactory.getLogger(EducationDetailController.class);

    @Autowired
    EducationService service;

    @PostMapping("/{userId}")
    List<EducationDto> addEducationDetails(@RequestBody @Valid List<EducationDto> dto, @PathVariable UUID userId){


        log.info("Adding education details for userId: {}", userId);

        List<EducationDto> savedList = service.saveEducationDetail(dto, userId);

        log.info("Education details added successfully for userId: {}", userId);
        return savedList;
    }

    @GetMapping("/{userId}")
    List<EducationDto> getEducationDetailById(@PathVariable UUID userId){
        log.info("Fetching education details for userId: {}", userId);

        List<EducationDto> educationList = service.getEducationDetailById(userId);

        log.info("Education details updated successfully for userId: {}", userId);


        return educationList;
    }

    @PutMapping("/{userId}")
    List<EducationDto> updateEducationDetails(@RequestBody @Valid List<EducationDto> dto, @PathVariable UUID userId){
        log.info("Updating education details for userId: {}", userId);

        List<EducationDto> updatedList = service.updateEducationDetail(dto, userId);

        log.info("Education details updated successfully for userId: {}", userId);
        return updatedList;
    }

}
