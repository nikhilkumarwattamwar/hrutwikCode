package com.loanapp.loanManagementSystem.mapper;

import com.loanapp.loanManagementSystem.dto.PersonalDto;
import com.loanapp.loanManagementSystem.entities.PersonalDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonalDetailsMapper {

    PersonalDto toPersonaldto(PersonalDetails details);

    PersonalDetails toEntity(PersonalDto dto);

    void updateEntityFromDto(PersonalDto dto, @MappingTarget PersonalDetails details);

}
