package com.loanapp.loanManagementSystem.contollers.user;

import com.loanapp.loanManagementSystem.dto.user.PersonalDto;
import com.loanapp.loanManagementSystem.service.user.PersonalDetailsService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user/personal")
public class PersonalDetailController {

    @Autowired
    private PersonalDetailsService service;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{id}")
    public PersonalDto saveAllDetails(@RequestBody @Valid PersonalDto dto,
                                      @PathVariable UUID id) {
        return service.saveDetailsById(dto, id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public PersonalDto getDetails(@PathVariable UUID id) {
        return service.getDetailsByID(id);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public PersonalDto updateDetails(@RequestBody @Valid PersonalDto dto,
                                     @PathVariable UUID id) {
        return service.updateDetails(dto, id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDetails(@PathVariable UUID id) {
        service.deleteDetailsByUserId(id);
        return ResponseEntity.ok("Personal details deleted successfully");
    }


}
