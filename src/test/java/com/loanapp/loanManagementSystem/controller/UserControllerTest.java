package com.loanapp.loanManagementSystem.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.loanapp.loanManagementSystem.contollers.user.UserController;
import com.loanapp.loanManagementSystem.dto.user.UserDto;
import com.loanapp.loanManagementSystem.service.user.UserService;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserDto sampleUser;

    @BeforeEach
    void setUp() {
        sampleUser = new UserDto();
        sampleUser.setId(UUID.randomUUID());
        sampleUser.setEmail("test@example.com");

    }

    @Test
    void testAddUser() {
        when(userService.createUser(any(UserDto.class))).thenReturn(sampleUser);

        UserDto result = userController.addUser(sampleUser);

        assertEquals(sampleUser.getEmail(), result.getEmail());
        verify(userService, times(1)).createUser(sampleUser);
    }

    @Test
    void testGetAllUserDetails() {
        List<UserDto> users = Arrays.asList(sampleUser);
        when(userService.getAllUsers()).thenReturn(users);

        List<UserDto> result = userController.getAllUserDetails();

        assertEquals(1, result.size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserDetailById() {
        UUID userId = sampleUser.getId();
        when(userService.getUserById(userId)).thenReturn(sampleUser);

        UserDto result = userController.getUserDetailById(userId);

        assertEquals(sampleUser.getId(), result.getId());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void testUpdateUserDetail() {
        UUID userId = sampleUser.getId();
        when(userService.updateUser(any(UserDto.class), eq(userId))).thenReturn(sampleUser);

        UserDto result = userController.updateUserdetail(sampleUser, userId);

        assertEquals(sampleUser.getEmail(), result.getEmail());
        verify(userService, times(1)).updateUser(sampleUser, userId);
    }

    @Test
    void testSoftDeleting() {
        UUID userId = sampleUser.getId();
        doNothing().when(userService).softDeleteUser(userId);

        ResponseEntity<String> response = userController.softDeleting(userId);

        assertEquals("User deleted successfully", response.getBody());
        verify(userService, times(1)).softDeleteUser(userId);
    }
}

