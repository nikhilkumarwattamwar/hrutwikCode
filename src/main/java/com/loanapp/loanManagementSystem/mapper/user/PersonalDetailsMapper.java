package com.loanapp.loanManagementSystem.mapper.user;

import com.loanapp.loanManagementSystem.dto.user.PersonalDto;
import com.loanapp.loanManagementSystem.entities.user.PersonalDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonalDetailsMapper {

    PersonalDto toPersonaldto(PersonalDetails details);

    PersonalDetails toEntity(PersonalDto dto);

    void updateEntityFromDto(PersonalDto dto, @MappingTarget PersonalDetails details);

}
