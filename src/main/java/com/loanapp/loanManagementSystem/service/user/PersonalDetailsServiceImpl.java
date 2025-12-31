package com.loanapp.loanManagementSystem.service.user;

import com.loanapp.loanManagementSystem.dto.user.PersonalDto;
import com.loanapp.loanManagementSystem.entities.user.User;
import com.loanapp.loanManagementSystem.mapper.user.PersonalDetailsMapper;
import com.loanapp.loanManagementSystem.entities.user.PersonalDetails;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import com.loanapp.loanManagementSystem.repository.PersonalDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonalDetailsServiceImpl implements PersonalDetailsService {

    private static final Logger log = LoggerFactory.getLogger(PersonalDetailsServiceImpl.class);

    @Autowired
    PersonalDetailsRepository personalDetailsRepository;

    @Autowired
    PersonalDetailsMapper mapper;

    @Autowired
    UserRepository userRepository;

    public PersonalDto saveDetailsById(PersonalDto dto, UUID id) {

        log.info("Saving personal details for user ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", id);
                    return new RuntimeException("ID does not exist");
                });
        PersonalDetails details = mapper.toEntity(dto);
        user.setPersonalDetails(details);

        details.setUser(user);

        PersonalDetails saved = personalDetailsRepository.save(details);

        log.info("Personal details saved successfully for user ID: {}", id);
        return mapper.toPersonaldto(saved);

    }

    public PersonalDto getDetailsByID(UUID id) {
        log.info("Fetching personal details for user ID: {}", id);

        PersonalDetails details = personalDetailsRepository.findByUserId(id).orElseThrow(() -> {
            log.error("Personal details not found for user ID: {}", id);
            return new RuntimeException("ID does not exists");
        });

        return mapper.toPersonaldto(details);
    }

    public PersonalDto updateDetails(PersonalDto dto, UUID id) {

        log.info("Updating personal details for user ID: {}", id);

        PersonalDetails existingDetails = personalDetailsRepository.findByUserId(id).orElseThrow(() -> {
            log.error("Update failed. Personal details not found for user ID: {}", id);
            return new RuntimeException("User Id not found");
        });

        mapper.updateEntityFromDto(dto, existingDetails);

        PersonalDetails updated = personalDetailsRepository.save(existingDetails);

        log.info("Personal details updated successfully for user ID: {}", id);


        return mapper.toPersonaldto(updated);

    }


}
