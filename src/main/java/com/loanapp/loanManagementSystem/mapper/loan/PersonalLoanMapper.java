package com.loanapp.loanManagementSystem.mapper.loan;

import com.loanapp.loanManagementSystem.dto.loan.PersonalLoanDto;
import com.loanapp.loanManagementSystem.entities.loan.PersonalLoan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonalLoanMapper {

    @Mapping(target = "loanId", ignore = true)
    PersonalLoan toEntity(PersonalLoanDto dto);

    PersonalLoanDto toDto(PersonalLoan entity);

    void updateEntity(PersonalLoanDto dto, @MappingTarget PersonalLoan entity);
}
