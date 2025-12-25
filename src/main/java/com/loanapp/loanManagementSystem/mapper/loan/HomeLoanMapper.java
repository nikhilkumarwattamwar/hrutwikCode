package com.loanapp.loanManagementSystem.mapper.loan;

import com.loanapp.loanManagementSystem.dto.loan.HomeLoanDto;
import com.loanapp.loanManagementSystem.entities.loan.HomeLoan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface HomeLoanMapper {
    @Mapping(target = "loanId", ignore = true)
    HomeLoan toEntity(HomeLoanDto dto);

    HomeLoanDto toDto(HomeLoan entity);

    void updateEntity(HomeLoanDto dto, @MappingTarget HomeLoan entity);
}
