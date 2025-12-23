package com.loanapp.loanManagementSystem.service;

import com.loanapp.loanManagementSystem.dto.AddressDto;
import com.loanapp.loanManagementSystem.dto.UserDto;
import com.loanapp.loanManagementSystem.entities.Address;
import com.loanapp.loanManagementSystem.entities.EducationDetails;
import com.loanapp.loanManagementSystem.enums.AddressType;
import com.loanapp.loanManagementSystem.exception.BadRequestException;
import com.loanapp.loanManagementSystem.exception.ResourceNotFoundException;
import com.loanapp.loanManagementSystem.mapper.AddressMapper;
import com.loanapp.loanManagementSystem.mapper.UserMapper;
import com.loanapp.loanManagementSystem.entities.User;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper mapper;

    @Autowired
    AddressMapper addressMapper;

    @Override
    public UserDto createUser(UserDto dto){
        log.info("Creating user with email: {}", dto.getEmail());

        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            log.warn("User creation failed. Email already exists : {}", dto.getEmail());
            throw new BadRequestException("Email already exists.");
        }

        if(userRepository.findByMobileNumber(dto.getMobileNumber()).isPresent()){
            log.warn("User creation failed. Mobile number already exists : {}", dto.getMobileNumber());
            throw  new BadRequestException("Mobile Number already exists.");
        }

       Boolean hasPermannent= dto.getAddressList().stream().filter(addressDto -> addressDto.getType()==AddressType.PERMANENT).findAny().isPresent();
       Boolean hasResidence=dto.getAddressList().stream().filter(addressDto -> addressDto.getType()==AddressType.RESIDENCE).findAny().isPresent();

       if(!hasResidence || !hasPermannent){
           log.warn("User creation failed. Both PERMANENT and RESIDENCE address required");
           throw new BadRequestException("Both Permanent and Residence address are required.");
       }

        User user=mapper.toEntity(dto);

       for(AddressDto addressDto:dto.getAddressList()){
           Address address=addressMapper.toEntity(addressDto);
           user.setAddressDetail(address);
       }

        User saved=userRepository.save(user);

       UserDto savedDto=mapper.toDto(saved);

        log.info("User created successfully with ID: {}", saved.getId());

        return savedDto;
    }

    @Override
    public List<UserDto> getAllUsers(){
        log.info("Fetching all users");

        List<User> applicantsList= userRepository.findAll();

        return mapper.toDtoList(applicantsList);
    }

    @Override
    public UserDto getUserById(UUID userId){

        log.info("Fetching user by ID: {}", userId);

        User user = userRepository.findById(userId).orElseThrow(()->{
            log.error("User not found with ID: {}", userId);
            return new ResourceNotFoundException("User ID not found");
        });

        return mapper.toDto(user);
    }

    @Override
    public UserDto updateUser(UserDto dto, UUID id){

        log.info("Updating user with ID: {}", id);

        User existingUser = userRepository.findById(id).orElseThrow(()->{
            log.error("User not found with ID : {}",id);
           return new ResourceNotFoundException("User not found");
        });

        mapper.updateEntityFromDto(dto, existingUser);

        if(dto.getAddressList()!=null){

            existingUser.getAddressList().clear();

            for(AddressDto addressDto:dto.getAddressList()){
                Address address=addressMapper.toEntity(addressDto);
                address.setUser(existingUser);
                existingUser.getAddressList().add(address);
            }

        }

        User updatedUser = userRepository.save(existingUser);

        log.info("User updated successfully with ID: {}", id);

        return mapper.toDto(updatedUser);

    }

    @Override
    public void softDeleteUser(UUID userId){

        log.info("Soft deleting user with ID: {}", userId);

        User user=userRepository.findByIdAndIsActiveTrue(userId).orElseThrow(()->{
            log.error("Delete failed. Active user not found with ID: {}", userId);
          return   new ResourceNotFoundException("User ID not found");
        });

        user.setActive(false);

        if(user.getAddressList()!=null){
            for (Address address : user.getAddressList()) {
                address.setActive(false);
            }
        }

        if(user.getEducationDetailsList()!=null){
            for(EducationDetails details:user.getEducationDetailsList()){
                details.setActive(false);
            }
        }

        if(user.getPersonalDetails()!=null){
            user.getPersonalDetails().setActive(false);
        }

        log.info("User soft deleted successfully with ID: {}", userId);

        userRepository.save(user);

    }

}
