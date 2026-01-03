package com.loanapp.loanManagementSystem.contollers.user;

import com.loanapp.loanManagementSystem.dto.loan.LoginRequestDto;
import com.loanapp.loanManagementSystem.dto.loan.LoginResponseDto;
import com.loanapp.loanManagementSystem.dto.user.UserDto;
import com.loanapp.loanManagementSystem.service.user.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public UserDto register(@RequestBody UserDto dto) {
        return userService.register(dto);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto) {
        return userService.login(dto);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/profile/{userId}")
    public UserDto addUserdetails(@PathVariable UUID userId, @RequestBody UserDto dto) {
        return userService.addUserDetails(userId, dto);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public UserDto getUserDetailById(@PathVariable UUID userId){
        return userService.getUserById(userId);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{userId}")
    public UserDto updateUserdetail(@RequestBody @Valid UserDto dto, @PathVariable UUID userId){
        return userService.updateUser(dto,userId);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> softDeleting(@PathVariable UUID userId){
        userService.softDeleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }


}
