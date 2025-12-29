package com.loanapp.loanManagementSystem.service;

import com.loanapp.loanManagementSystem.dto.loan.LoanDto;
import com.loanapp.loanManagementSystem.dto.user.UserDto;
import com.loanapp.loanManagementSystem.entities.loan.Loan;
import com.loanapp.loanManagementSystem.entities.user.User;
import com.loanapp.loanManagementSystem.enums.LoanStatus;
import com.loanapp.loanManagementSystem.exception.ResourceNotFoundException;
import com.loanapp.loanManagementSystem.mapper.loan.LoanMapper;
import com.loanapp.loanManagementSystem.mapper.user.UserMapper;
import com.loanapp.loanManagementSystem.repository.LoanRepository;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import com.loanapp.loanManagementSystem.service.admin.AdminServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @InjectMocks
    AdminServiceImpl service;

    @Mock
    LoanRepository loanRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    LoanMapper loanMapper;

    @Mock
    UserMapper userMapper;

    @Test
    @DisplayName("Should get all existing loans")
    void testGetAllLoan(){
        Loan loan = new Loan();
        LoanDto dto= new LoanDto();

        when(loanRepository.findAll()).thenReturn(List.of(loan));
        when(loanMapper.toDto(loan)).thenReturn(dto);

        List<LoanDto> loans=service.getAllLoan();


        assertNotNull(loans);
        verify(loanRepository).findAll();

    }

    @Test
    @DisplayName("Shound get loan details by Loan ID")
    void testGetLoanByLoanId(){
        UUID loanID=UUID.randomUUID();
        Loan loan= new Loan();
        loan.setLoanId(loanID);
        loan.setActive(true);
        LoanDto dto= new LoanDto();
        when(loanRepository.findByLoanIdAndIsActiveTrue(loanID)).thenReturn(Optional.of(loan));
        when(loanMapper.toDto(loan)).thenReturn(dto);

        LoanDto result=service.getLoanByLoanId(loanID);

        assertNotNull(result);
        verify(loanRepository).findByLoanIdAndIsActiveTrue(loanID);

    }

    @Test
    @DisplayName("Throw exception when loan id is not found")
    void testLoanIDNotFound(){
        UUID random=UUID.randomUUID();
        when(loanRepository.findByLoanIdAndIsActiveTrue(random)).thenReturn(Optional.empty());
        ResourceNotFoundException ex=assertThrows(ResourceNotFoundException.class,()->service.getLoanByLoanId(random));

        assertEquals("Loan ID not found",ex.getMessage());
        verify(loanMapper,never()).toDto(any());
    }

    @Test
    @DisplayName("Should approve the loan")
    void testApproveLoan(){
        UUID id=UUID.randomUUID();

        Loan loan= new Loan();
        loan.setLoanStaus(LoanStatus.APPROVED);
        LoanDto dto= new LoanDto();

        when(loanRepository.findByLoanIdAndIsActiveTrue(id)).thenReturn(Optional.of(loan));
        when(loanRepository.save(loan)).thenReturn(loan);
        when(loanMapper.toDto(loan)).thenReturn(dto);

        LoanDto result=service.approveLoan(id);

        assertEquals(LoanStatus.APPROVED,loan.getLoanStaus());
        assertNotNull(result);
        verify(loanRepository).findByLoanIdAndIsActiveTrue(id);

    }

    @Test
    @DisplayName("should reject the loan")
    void testRejectLoan(){
        UUID id=UUID.randomUUID();

        Loan loan= new Loan();
        loan.setLoanStaus(LoanStatus.REJECTED);

        LoanDto dto= new LoanDto();

        when(loanRepository.findByLoanIdAndIsActiveTrue(id)).thenReturn(Optional.of(loan));
        when(loanRepository.save(loan)).thenReturn(loan);
        when(loanMapper.toDto(loan)).thenReturn(dto);

        LoanDto result=service.rejectLoan(id,"xyz");

        assertNotNull( result);
        assertEquals(LoanStatus.REJECTED,loan.getLoanStaus());
        assertEquals("xyz",loan.getRejectionReason());

        verify(loanRepository).findByLoanIdAndIsActiveTrue(id);
        verify(loanRepository).save(loan);
    }

    @Test
    @DisplayName("Should get all users")
    void testGetAllUsers(){
        UUID random=UUID.randomUUID();
        User user= new User();
        UserDto dto= new UserDto();

        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.toDto(user)).thenReturn(dto);

        List<UserDto> dtoList=service.getAllUsers();

        assertNotNull(dtoList);
        assertEquals(1,dtoList.size());
        verify(userRepository).findAll();

    }
}
