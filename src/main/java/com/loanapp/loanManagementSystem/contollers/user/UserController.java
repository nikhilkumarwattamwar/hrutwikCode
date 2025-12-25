package com.loanapp.loanManagementSystem.contollers.user;

import com.loanapp.loanManagementSystem.dto.user.UserDto;
import com.loanapp.loanManagementSystem.service.user.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping
    public UserDto addUser(@RequestBody @Valid UserDto userDto){
        log.info("Adding user with email :{} "+userDto.getEmail());
        return userService.createUser(userDto);
    }


    @GetMapping("/all")
    public List<UserDto> getAllUserDetails(){
        log.info("fetch all user");
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUserDetailById(@PathVariable UUID userId){
        log.info("Fetching user with id :{}"+userId);
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public UserDto updateUserdetail(@RequestBody @Valid UserDto dto, @PathVariable UUID userId){
        log.info("Updating user with ID:{} ", userId);
        return userService.updateUser(dto,userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> softDeleting(@PathVariable UUID userId){
        log.warn("Soft deleting user with ID:{} ", userId);
        userService.softDeleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }


}
