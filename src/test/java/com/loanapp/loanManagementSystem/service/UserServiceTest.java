package com.loanapp.loanManagementSystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.loanapp.loanManagementSystem.dto.user.AddressDto;
import com.loanapp.loanManagementSystem.entities.user.*;
import com.loanapp.loanManagementSystem.enums.AddressType;
import com.loanapp.loanManagementSystem.exception.BadRequestException;
import com.loanapp.loanManagementSystem.exception.ResourceNotFoundException;
import com.loanapp.loanManagementSystem.service.user.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.loanapp.loanManagementSystem.dto.user.UserDto;
import com.loanapp.loanManagementSystem.mapper.user.AddressMapper;
import com.loanapp.loanManagementSystem.mapper.user.UserMapper;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper mapper;

    @Mock
    AddressMapper addressMapper;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("Adding user details")
    void testCreateUser() {
        UserDto dto = new UserDto();
        dto.setEmail("abcd@gmail.com");
        dto.setMobileNumber("1234567890");

        AddressDto permanent = new AddressDto();
        permanent.setType(AddressType.PERMANENT);
        AddressDto residence = new AddressDto();
        residence.setType(AddressType.RESIDENCE);
        dto.setAddressList(List.of(permanent, residence));

        User user = new User();

        when(addressMapper.toEntity(any())).thenReturn(new Address());
        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findByMobileNumber(dto.getMobileNumber())).thenReturn(Optional.empty());
        when(mapper.toDto(user)).thenReturn(dto);
        when(mapper.toEntity(dto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        UserDto result = userService.addUserDetails(UUID.randomUUID(),dto);

        assertNotNull(result);
        assertEquals("abcd@gmail.com", result.getEmail());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Fetching all users details")
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
    @DisplayName("Fetching user details using user id")
    void testGetUserById() {
        UUID id = UUID.randomUUID();
        User user = new User();
        UserDto dto = new UserDto();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(mapper.toDto(user)).thenReturn(dto);

        UserDto dto1 = userService.getUserById(id);

        assertNotNull(dto1);
    }

    @Test
    @DisplayName("Updating user details when address is null")
    void testUpdateUser() {
        UUID id = UUID.randomUUID();
        UserDto dto = new UserDto();
        dto.setEmail("new@gmail.com");

        User user = new User();
        user.setEmail("old@gamil.com");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(mapper.toDto(any(User.class))).thenReturn(dto);

        UserDto result = userService.updateUser(dto, id);

        assertNotNull(result);

        assertEquals("new@gmail.com", result.getEmail());
    }

    @Test
    @DisplayName("Updating user details when address is present")
    void updateWhileAddressExist() {
        UUID id = UUID.randomUUID();

        AddressDto addressDto = new AddressDto();
        addressDto.setType(AddressType.PERMANENT);

        UserDto dto = new UserDto();
        dto.setEmail("new@gmail.com");
        dto.setAddressList(List.of(addressDto));

        User user = new User();
        user.setEmail("old@gmail.com");
        user.setAddressList(new ArrayList<>());

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(addressMapper.toEntity(addressDto)).thenReturn(new Address());
        when(userRepository.save(user)).thenReturn(user);
        when(mapper.toDto(user)).thenReturn(dto);

        UserDto result = userService.updateUser(dto, id);

        assertNotNull(result);
        assertEquals(1, user.getAddressList().size());
    }


    @Test
    @DisplayName("Deleting user details")
    void testSoftDeletingUser() {
        UUID id = UUID.randomUUID();

        User user = new User();
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

    @Test
    @DisplayName("Throw exception if email already exists")
    void testEmailExists() {
        UUID id = UUID.randomUUID();
        UserDto userDto = mock(UserDto.class);
        userDto.setEmail("xyz@gmail.com");
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(new User()));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> userService.addUserDetails(UUID.randomUUID(),userDto));

        assertEquals("Email already exists.", exception.getMessage());
    }

    @Test
    @DisplayName("Throw exception if mobile number already exists")
    void testMobileNumberExists() {
        UUID id = UUID.randomUUID();
        UserDto userDto = mock(UserDto.class);
        userDto.setMobileNumber("1234567890");
        when(userRepository.findByMobileNumber(userDto.getMobileNumber())).thenReturn(Optional.of(new User()));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> userService.addUserDetails(UUID.randomUUID(),userDto));

        assertEquals("Mobile Number already exists.", exception.getMessage());
    }

    @Test
    @DisplayName("throw exception if both address types are not present")
    void testAddressTypeMissing() {
        AddressDto addressDto = new AddressDto();
        addressDto.setType(AddressType.PERMANENT);

        UserDto userDto = new UserDto();
        userDto.setEmail("xyz@gmail.com");
        userDto.setMobileNumber("1234567890");
        userDto.setAddressList(List.of(addressDto));

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        when(userRepository.findByMobileNumber(anyString())).thenReturn(Optional.empty());

        BadRequestException exception = assertThrows(BadRequestException.class, () -> userService.addUserDetails(UUID.randomUUID(),userDto));

        assertEquals("Both Permanent and Residence address are required.", exception.getMessage());
    }

    @Test
    @DisplayName("User id not found while fetching the user details")
    void testUserIdNotFound() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(id));
        assertEquals("User ID not found", exception.getMessage());
    }

    @Test
    @DisplayName("User id not found while updating the user details")
    void testUpdate_UserIdNotFound() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(new UserDto(), id));
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    @DisplayName("User id not found while deleting the details")
    void testDelete_UserIdNotFound() {
        UUID id = UUID.randomUUID();
        when(userRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> userService.softDeleteUser(id));
        assertEquals("User ID not found", exception.getMessage());
    }

}
