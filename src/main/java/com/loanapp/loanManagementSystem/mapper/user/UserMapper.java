package com.loanapp.loanManagementSystem.mapper.user;

import com.loanapp.loanManagementSystem.dto.user.UserDto;
import com.loanapp.loanManagementSystem.entities.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EducationMapper.class, CourseMapper.class, AddressMapper.class, PersonalDetailsMapper.class})
public interface UserMapper {


    @Mapping(source = "addressList", target = "addressList")
    UserDto toDto(User user);


    @Mapping(target = "addressList", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserDto dto);

    List<UserDto> toDtoList(List<User> userList);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(UserDto dto, @MappingTarget User user);

}
