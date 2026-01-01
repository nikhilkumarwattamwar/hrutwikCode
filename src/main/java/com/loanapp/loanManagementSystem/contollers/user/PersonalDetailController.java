package com.loanapp.loanManagementSystem.contollers.user;

import com.loanapp.loanManagementSystem.dto.user.PersonalDto;
import com.loanapp.loanManagementSystem.service.user.PersonalDetailsService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user/personal")
public class PersonalDetailController {

    @Autowired
    private PersonalDetailsService service;

    @PostMapping("/{id}")
    public PersonalDto saveAllDetails(@RequestBody @Valid PersonalDto dto,
                                      @PathVariable UUID id) {
        return service.saveDetailsById(dto, id);
    }

    @GetMapping("/{id}")
    public PersonalDto getDetails(@PathVariable UUID id) {
        return service.getDetailsByID(id);
    }

    @PutMapping("/{id}")
    public PersonalDto updateDetails(@RequestBody @Valid PersonalDto dto,
                                     @PathVariable UUID id) {
        return service.updateDetails(dto, id);
    }


}
