package com.loanapp.loanManagementSystem.service.user;

import com.loanapp.loanManagementSystem.dto.loan.LoginRequestDto;
import com.loanapp.loanManagementSystem.dto.loan.LoginResponseDto;
import com.loanapp.loanManagementSystem.dto.user.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto register(UserDto dto);

    UserDto addUserDetails(UUID userId, UserDto dto);

    List<UserDto> getAllUsers();

    UserDto getUserById(UUID userId);

    UserDto updateUser(UserDto dto, UUID userId);

    void softDeleteUser(UUID userId);

    LoginResponseDto login(LoginRequestDto dto);


}
