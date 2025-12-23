package com.loanapp.loanManagementSystem.mapper;

import com.loanapp.loanManagementSystem.dto.CourseDto;
import com.loanapp.loanManagementSystem.entities.CourseDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDetails toEntity(CourseDto dto);

    CourseDto toDto(CourseDetails details);

    void updateFromDtoToEntity(CourseDto dto,@MappingTarget CourseDetails details);

}
