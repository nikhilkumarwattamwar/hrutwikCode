package com.loanapp.loanManagementSystem.service.user;

import com.loanapp.loanManagementSystem.dto.loan.LoginRequestDto;
import com.loanapp.loanManagementSystem.dto.loan.LoginResponseDto;
import com.loanapp.loanManagementSystem.dto.user.AddressDto;
import com.loanapp.loanManagementSystem.dto.user.UserDto;
import com.loanapp.loanManagementSystem.entities.user.Address;
import com.loanapp.loanManagementSystem.entities.user.EducationDetails;
import com.loanapp.loanManagementSystem.enums.AddressType;
import com.loanapp.loanManagementSystem.exception.BadRequestException;
import com.loanapp.loanManagementSystem.exception.ResourceNotFoundException;
import com.loanapp.loanManagementSystem.integrate.FinClient;
import com.loanapp.loanManagementSystem.mapper.user.AddressMapper;
import com.loanapp.loanManagementSystem.mapper.user.UserMapper;
import com.loanapp.loanManagementSystem.entities.user.User;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper mapper;
    AddressMapper addressMapper;
    FinClient finClient;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper, AddressMapper addressMapper, FinClient finClient) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.addressMapper = addressMapper;
        this.finClient = finClient;
    }

    @Transactional
    @Override
    public UserDto register(UserDto dto) {
        FinClient.AuthRequest request = new FinClient.AuthRequest();
        request.setName(dto.getName());
        request.setEmail(dto.getEmail());
        request.setPassword(dto.getPassword());
        request.setRole(dto.getRole().name());

        try {
            finClient.register(request)
                    .onErrorMap(ex -> new RuntimeException("Auth service unavailable"))
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("User registration failed in auth service");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setActive(true);

        return mapper.toDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public LoginResponseDto login(LoginRequestDto dto) {

        if (dto.getUsername() == null || dto.getPassword() == null) {
            throw new BadRequestException("Email and password are required");
        }

        LoginResponseDto response = finClient.login(dto)
                .onErrorMap(ex -> new RuntimeException("Auth service unavailable"))
                .block();

        if (response == null || response.getToken() == null) {
            throw new BadRequestException("Invalid credentials");
        }

        return response;
    }


    @Transactional
    public UserDto addUserDetails(UUID userId, UserDto dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            return new ResourceNotFoundException("User id not found");
        });

        user.setCkycNo(dto.getCkycNo());
        user.setCustomerFetchType(dto.getCustomerFetchType());
        user.setCustomerType(dto.getCustomerType());
        user.setFathersName(dto.getFathersName());
        user.setMothersName(dto.getMothersName());
        user.setGuardianName(dto.getGuardianName());
        user.setNationality(dto.getNationality());
        user.setDateOfBirth(dto.getDateOfBirth());

        Boolean hasPermannent = dto.getAddressList().stream().filter(addressDto -> addressDto.getType() == AddressType.PERMANENT).findAny().isPresent();
        Boolean hasResidence = dto.getAddressList().stream().filter(addressDto -> addressDto.getType() == AddressType.RESIDENCE).findAny().isPresent();

        if (!hasResidence || !hasPermannent) {
            throw new BadRequestException("Both Permanent and Residence address are required.");
        }

        for (AddressDto addressDto : dto.getAddressList()) {
            Address address = addressMapper.toEntity(addressDto);
            user.setAddressDetail(address);
        }

        User saved = userRepository.save(user);

        UserDto savedDto = mapper.toDto(saved);

        return savedDto;

    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getAllUsers() {

        List<User> applicantsList = userRepository.findAll();

        return mapper.toDtoList(applicantsList);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUserById(UUID userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> {
            return new ResourceNotFoundException("User ID not found");
        });

        return mapper.toDto(user);
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto dto, UUID id) {

        User existingUser = userRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("User not found");
        });

        mapper.updateEntityFromDto(dto, existingUser);

        if (dto.getAddressList() != null) {

            existingUser.getAddressList().clear();

            for (AddressDto addressDto : dto.getAddressList()) {
                Address address = addressMapper.toEntity(addressDto);
                address.setUser(existingUser);
                existingUser.getAddressList().add(address);
            }

        }

        User updatedUser = userRepository.save(existingUser);

        return mapper.toDto(updatedUser);

    }

    @Transactional
    @Override
    public void softDeleteUser(UUID userId) {

        User user = userRepository.findByIdAndIsActiveTrue(userId).orElseThrow(() -> {
            return new ResourceNotFoundException("User ID not found");
        });

        user.setActive(false);

        if (user.getAddressList() != null) {
            for (Address address : user.getAddressList()) {
                address.setActive(false);
            }
        }

        if (user.getEducationDetailsList() != null) {
            for (EducationDetails details : user.getEducationDetailsList()) {
                details.setActive(false);
            }
        }

        if (user.getPersonalDetails() != null) {
            user.getPersonalDetails().setActive(false);
        }

        userRepository.save(user);

    }

}
