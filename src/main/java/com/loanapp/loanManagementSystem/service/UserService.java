package com.loanapp.loanManagementSystem.service;

import com.loanapp.loanManagementSystem.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto createUser(UserDto dto);

    List<UserDto> getAllUsers();

    UserDto getUserById(UUID userId);

    UserDto updateUser(UserDto dto, UUID userId);

    void softDeleteUser(UUID userId);

}
