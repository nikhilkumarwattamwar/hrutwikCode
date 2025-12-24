package com.loanapp.loanManagementSystem.contollers;

import com.loanapp.loanManagementSystem.dto.PersonalDto;
import com.loanapp.loanManagementSystem.service.PersonalDetailsService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user/personal")
public class PersonalDetailController {

   private static final Logger log= LoggerFactory.getLogger(PersonalDetailController.class);

    @Autowired
   private PersonalDetailsService service;

    @PostMapping("/{id}")
    public PersonalDto saveAllDetails(@RequestBody @Valid PersonalDto dto, @PathVariable UUID id){
        log.info("Saving personal details for id: {}", id);
        return   service.saveDetailsById(dto,id);
    }

    @GetMapping("/{id}")
   public PersonalDto getDetails(@PathVariable UUID id){
        log.info("Fetching personal details for id: {}", id);
        return service.getDetailsByID(id);
    }

    @PutMapping("/{id}")
    public PersonalDto updateDetails(@RequestBody @Valid PersonalDto dto, @PathVariable UUID id){
        log.info("Updating personal details for id: {}", id);

        return service.updateDetails(dto,id);
    }


}
