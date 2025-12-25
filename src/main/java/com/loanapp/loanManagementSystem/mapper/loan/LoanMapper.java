package com.loanapp.loanManagementSystem.mapper.loan;

import com.loanapp.loanManagementSystem.dto.loan.LoanDto;
import com.loanapp.loanManagementSystem.entities.loan.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(target = "loanId", ignore = true)
    @Mapping(target = "user", ignore = true)
    Loan toEntity(LoanDto dto);

    LoanDto toDto(Loan loan);

}
