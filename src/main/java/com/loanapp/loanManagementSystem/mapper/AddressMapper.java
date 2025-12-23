package com.loanapp.loanManagementSystem.mapper;

import com.loanapp.loanManagementSystem.dto.AddressDto;
import com.loanapp.loanManagementSystem.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(AddressDto addressDto);

    AddressDto toDto(Address address);


    void updateEntityFromDto(AddressDto dto, @MappingTarget Address address);


    List<AddressDto> toDtoList(List<Address> addressList);
}
