package com.loanapp.loanManagementSystem.mapper.user;

import com.loanapp.loanManagementSystem.dto.user.AddressDto;
import com.loanapp.loanManagementSystem.entities.user.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(AddressDto addressDto);

    AddressDto toDto(Address address);


    void updateEntityFromDto(AddressDto dto, @MappingTarget Address address);


    List<AddressDto> toDtoList(List<Address> addressList);
}
