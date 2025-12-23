package com.loanapp.loanManagementSystem.mapper;

import com.loanapp.loanManagementSystem.dto.EducationDto;
import com.loanapp.loanManagementSystem.entities.EducationDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationMapper {
    EducationDto toDto(EducationDetails details);

    EducationDetails toEntity(EducationDto dto);

    void updateFromDtoToEntity(EducationDto dto, @MappingTarget EducationDetails details);

    List<EducationDto> toDtoList(List<EducationDetails> detailsList);

}
