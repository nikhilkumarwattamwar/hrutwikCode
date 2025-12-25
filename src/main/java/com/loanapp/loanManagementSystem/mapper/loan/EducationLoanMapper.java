package com.loanapp.loanManagementSystem.mapper.loan;

import com.loanapp.loanManagementSystem.dto.loan.EducationLoanDto;
import com.loanapp.loanManagementSystem.dto.loan.HomeLoanDto;
import com.loanapp.loanManagementSystem.entities.loan.EducationLoan;
import com.loanapp.loanManagementSystem.entities.loan.HomeLoan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EducationLoanMapper {


    @Mapping(target = "loanId", ignore = true)
    EducationLoan toEntity(EducationLoanDto dto);

    EducationLoanDto toDto(EducationLoan entity);

    void updateEntity(EducationLoanDto dto, @MappingTarget EducationLoan entity);

}
