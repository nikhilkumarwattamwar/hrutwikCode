package com.loanapp.loanManagementSystem.mapper.user;

import com.loanapp.loanManagementSystem.dto.user.CourseDto;
import com.loanapp.loanManagementSystem.entities.user.CourseDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDetails toEntity(CourseDto dto);

    CourseDto toDto(CourseDetails details);

    void updateFromDtoToEntity(CourseDto dto,@MappingTarget CourseDetails details);

}
