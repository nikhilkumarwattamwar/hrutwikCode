package com.loanapp.loanManagementSystem.contollers.user;

import com.loanapp.loanManagementSystem.dto.user.EducationDto;
import com.loanapp.loanManagementSystem.service.user.EducationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user/education")
public class EducationDetailController {

    @Autowired
    EducationService service;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{userId}")
    public List<EducationDto> addEducationDetails(@RequestBody @Valid List<EducationDto> dto,
                                                  @PathVariable UUID userId) {
        List<EducationDto> savedList = service.saveEducationDetail(dto, userId);
        return savedList;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public List<EducationDto> getEducationDetailById(@PathVariable UUID userId) {
        List<EducationDto> educationList = service.getEducationDetailById(userId);
        return educationList;
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{userId}")
    public List<EducationDto> updateEducationDetails(@RequestBody @Valid List<EducationDto> dto,
                                                     @PathVariable UUID userId) {
        List<EducationDto> updatedList = service.updateEducationDetail(dto, userId);
        return updatedList;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteAllEducation(@PathVariable UUID userId) {
        service.deleteAllEducationByUserId(userId);
        return ResponseEntity.ok("All education records deleted successfully");
    }

}
