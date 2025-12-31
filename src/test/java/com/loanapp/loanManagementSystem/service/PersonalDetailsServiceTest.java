package com.loanapp.loanManagementSystem.service;

import com.loanapp.loanManagementSystem.dto.user.PersonalDto;
import com.loanapp.loanManagementSystem.entities.user.PersonalDetails;
import com.loanapp.loanManagementSystem.entities.user.User;
import com.loanapp.loanManagementSystem.exception.ResourceNotFoundException;
import com.loanapp.loanManagementSystem.mapper.user.PersonalDetailsMapper;
import com.loanapp.loanManagementSystem.repository.PersonalDetailsRepository;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import com.loanapp.loanManagementSystem.service.user.PersonalDetailsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonalDetailsServiceTest {

    @InjectMocks
    PersonalDetailsServiceImpl personalDetailsService;
    @Mock
    PersonalDetailsMapper mapper;
    @Mock
    PersonalDetailsRepository detailsRepository;
    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("saveDetailsById : should save personal details for existing user ")
    void testSaveDetailsById() {
        UUID id = UUID.randomUUID();

        PersonalDto personalDto = new PersonalDto();
        User user = new User();
        PersonalDetails details = new PersonalDetails();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(mapper.toEntity(personalDto)).thenReturn(details);
        when(detailsRepository.save(details)).thenReturn(details);
        when(mapper.toPersonaldto(details)).thenReturn(personalDto);

        PersonalDto result = personalDetailsService.saveDetailsById(personalDto, id);

        assertNotNull(result);
        verify(userRepository).findById(id);
        verify(detailsRepository).save(details);

    }

    @Test
    @DisplayName("getDetailsByID: should fetch personal details of user using id ")
    void testGetDetailsById() {
        UUID id = UUID.randomUUID();

        PersonalDetails details = new PersonalDetails();
        PersonalDto dto = new PersonalDto();

        when(detailsRepository.findByUserId(id)).thenReturn(Optional.of(details));
        when(mapper.toPersonaldto(details)).thenReturn(dto);

        PersonalDto result = personalDetailsService.getDetailsByID(id);

        assertNotNull(result);
        verify(detailsRepository).findByUserId(id);

    }

    @Test
    @DisplayName("updateDetails : should update details of existing user")
    void testUpdateDetails() {
        UUID id = UUID.randomUUID();
        PersonalDetails details = new PersonalDetails();
        PersonalDto dto = new PersonalDto();

        when(detailsRepository.findByUserId(id)).thenReturn(Optional.of(details));
        when(detailsRepository.save(details)).thenReturn(details);
        when(mapper.toPersonaldto(details)).thenReturn(dto);

        PersonalDto result = personalDetailsService.updateDetails(dto, id);

        verify(detailsRepository).findByUserId(id);
        verify(detailsRepository).save(details);

    }

    @Test
    @DisplayName("Throw exception when user id is not found")
    void testUserIdNotFound() {
        UUID id = UUID.randomUUID();

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> personalDetailsService.saveDetailsById(new PersonalDto(), id));

        assertEquals("ID does not exist", exception.getMessage());
    }

    @Test
    @DisplayName("Throw exception when user id not found while fetching personal details")
    void testGet_UserIdNotFound() {
        UUID id = UUID.randomUUID();

        when(detailsRepository.findByUserId(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> personalDetailsService.getDetailsByID(id));

        assertEquals("ID does not exists", exception.getMessage());
    }

    @Test
    @DisplayName("Throw exception when user id not found while updating personal details")
    void testUpdate_UserIdNotFound() {
        UUID id = UUID.randomUUID();

        when(detailsRepository.findByUserId(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> personalDetailsService.updateDetails(new PersonalDto(), id));

        assertEquals("User Id not found", exception.getMessage());
    }


}
