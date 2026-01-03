package com.loanapp.loanManagementSystem.service.admin;

import com.loanapp.loanManagementSystem.dto.loan.LoanDto;
import com.loanapp.loanManagementSystem.dto.user.UserDto;
import com.loanapp.loanManagementSystem.entities.loan.Loan;
import com.loanapp.loanManagementSystem.enums.LoanStatus;
import com.loanapp.loanManagementSystem.exception.ResourceNotFoundException;
import com.loanapp.loanManagementSystem.mapper.loan.LoanMapper;
import com.loanapp.loanManagementSystem.mapper.user.UserMapper;
import com.loanapp.loanManagementSystem.repository.LoanRepository;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    LoanMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<LoanDto> getAllLoan() {
        List<Loan> loans = loanRepository.findAll();

        return loans.stream().map(loan -> mapper.toDto(loan)).toList();

    }

    @Transactional(readOnly = true)
    @Override
    public LoanDto getLoanByLoanId(UUID loanId) {
        Loan loans = loanRepository.findByLoanIdAndIsActiveTrue(loanId).orElseThrow(() -> {
            return new ResourceNotFoundException("Loan ID not found");
        });

        return mapper.toDto(loans);
    }

    @Transactional
    @Override
    public LoanDto approveLoan(UUID loanId) {
        Loan existingLoan = loanRepository.findByLoanIdAndIsActiveTrue(loanId).orElseThrow(() -> {
            return new ResourceNotFoundException("Loan ID not found");
        });

        existingLoan.setLoanStaus(LoanStatus.APPROVED);
        Loan updated = loanRepository.save(existingLoan);
        return mapper.toDto(updated);

    }

    @Transactional
    @Override
    public LoanDto rejectLoan(UUID loanId, String reason) {
        Loan existingLoan = loanRepository.findByLoanIdAndIsActiveTrue(loanId).orElseThrow(() -> {
            return new ResourceNotFoundException("Loan ID not found");
        });

        existingLoan.setLoanStaus(LoanStatus.REJECTED);
        existingLoan.setRejectionReason(reason);

        Loan updated = loanRepository.save(existingLoan);
        return mapper.toDto(updated);
    }

    @Transactional
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> userMapper.toDto(user)).toList();
    }
}
