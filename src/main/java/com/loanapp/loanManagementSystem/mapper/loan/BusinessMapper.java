package com.loanapp.loanManagementSystem.mapper.loan;

import com.loanapp.loanManagementSystem.dto.loan.BusinessLoanDto;
import com.loanapp.loanManagementSystem.dto.loan.EducationLoanDto;
import com.loanapp.loanManagementSystem.entities.loan.BusinessLoan;
import com.loanapp.loanManagementSystem.entities.loan.EducationLoan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BusinessMapper {
    @Mapping(target = "loanId", ignore = true)
    BusinessLoanDto toDto(BusinessLoan loan);

    BusinessLoan toEntity(BusinessLoanDto businessLoanDto);

    void updateEntity(BusinessLoanDto dto, @MappingTarget BusinessLoan entity);
}
