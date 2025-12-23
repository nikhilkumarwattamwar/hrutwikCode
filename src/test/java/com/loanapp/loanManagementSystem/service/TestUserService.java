package com.loanapp.loanManagementSystem.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.loanapp.loanManagementSystem.dto.AddressDto;
import com.loanapp.loanManagementSystem.entities.*;
import com.loanapp.loanManagementSystem.enums.AddressType;
import org.junit.jupiter.api.Test;
import com.loanapp.loanManagementSystem.dto.UserDto;
import com.loanapp.loanManagementSystem.mapper.AddressMapper;
import com.loanapp.loanManagementSystem.mapper.UserMapper;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class TestUserService {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper mapper;

    @Mock
    AddressMapper addressMapper;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void testCreateUser(){
        UserDto dto= new UserDto();
        dto.setEmail("abcd@gmail.com");
        dto.setMobileNumber("1234567890");

        AddressDto permanent = new AddressDto();
        permanent.setType(AddressType.PERMANENT);
        AddressDto residence = new AddressDto();
        residence.setType(AddressType.RESIDENCE);
        dto.setAddressList(List.of(permanent, residence));

        User user= new User();

        when(addressMapper.toEntity(any())).thenReturn(new Address());
        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findByMobileNumber(dto.getMobileNumber())).thenReturn(Optional.empty());
        when(mapper.toDto(user)).thenReturn(dto);
        when(mapper.toEntity(dto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        UserDto result=userService.createUser(dto);

        assertNotNull(result);
        assertEquals("abcd@gmail.com", result.getEmail());

        verify(userRepository,times(1)).save(user);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = List.of(new User(), new User());
        List<UserDto> dtoList = List.of(new UserDto(), new UserDto());

        when(userRepository.findAll()).thenReturn(users);
        when(mapper.toDtoList(users)).thenReturn(dtoList);

        List<UserDto> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById(){
        UUID id=UUID.randomUUID();
        User user= new User();
        UserDto dto= new UserDto();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(mapper.toDto(user)).thenReturn(dto);

        UserDto dto1=userService.getUserById(id);

        assertNotNull(dto1);
    }

    @Test
    void testUpdateUser(){
        UUID id=UUID.randomUUID();
        UserDto dto= new UserDto();
        dto.setEmail("new@gmail.com");

        User user= new User();
        user.setEmail("old@gamil.com");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(mapper.toDto(any(User.class))).thenReturn(dto);

        UserDto dto1=userService.updateUser(dto,id);

        assertNotNull(dto1);

        assertEquals("new@gmail.com",dto1.getEmail());
    }


    @Test
    void testSoftDeletingUser(){
        UUID id=UUID.randomUUID();

        User  user= new User();
        user.setId(id);
        user.setActive(true);
        user.setAddressList(Arrays.asList(new Address()));
        user.setEducationDetailsList(Arrays.asList(new EducationDetails()));
        user.setPersonalDetails(new PersonalDetails());
        user.setCourseDetails(new CourseDetails());

        when(userRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.of(user));

        userService.softDeleteUser(id);

        assertFalse(user.isActive());
        verify(userRepository).save(user);

    }




}
