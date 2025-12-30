package com.loanapp.loanManagementSystem.controller;

import com.loanapp.loanManagementSystem.contollers.admin.AdminController;
import com.loanapp.loanManagementSystem.dto.loan.LoanDto;
import com.loanapp.loanManagementSystem.dto.user.UserDto;
import com.loanapp.loanManagementSystem.entities.loan.Loan;
import com.loanapp.loanManagementSystem.entities.user.User;
import com.loanapp.loanManagementSystem.repository.LoanRepository;
import com.loanapp.loanManagementSystem.service.admin.AdminService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
    @InjectMocks
    AdminController controller;

    @Mock
    AdminService service;

    @Mock
    LoanRepository repository;

    @Test
    @DisplayName("Shoud get all existing loans")
    void testGetAllLoans() {

        LoanDto dto = new LoanDto();

        when(service.getAllLoan()).thenReturn(List.of(dto));

        List<LoanDto> result = controller.getAllLoans();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(service, times(1)).getAllLoan();

    }

    @Test
    @DisplayName("Should get loan details by loan id")
    void testGetLoanByLoanId() {
        UUID id = UUID.randomUUID();
        LoanDto dto = new LoanDto();

        when(service.getLoanByLoanId(id)).thenReturn(dto);

        LoanDto result = controller.getLoanByLoanId(id);

        assertNotNull(result);
        verify(service, times(1)).getLoanByLoanId(id);
    }

    @Test
    @DisplayName("Should approve the loan")
    void testApproveTheLoan() {
        UUID id = UUID.randomUUID();
        LoanDto dto = new LoanDto();
        when(service.approveLoan(id)).thenReturn(dto);

        LoanDto result = controller.apprroveLoan(id);
        assertNotNull(result);
        verify(service, times(1)).approveLoan(id);
    }

    @Test
    @DisplayName("Should reject the loan")
    void testRejectTheLoan() {
        UUID id = UUID.randomUUID();
        String reason = "low credit score";

        LoanDto dto = new LoanDto();
        dto.setLoanId(id);

        when(service.rejectLoan(id, reason)).thenReturn(dto);

        LoanDto result = controller.rejectLoan(id, reason);

        verify(service, times(1)).rejectLoan(id, reason);
    }

    @Test
    @DisplayName("Should get all users")
    void testGetAllUsers() {
        UUID id = UUID.randomUUID();
        UserDto dto = new UserDto();

        when(service.getAllUsers()).thenReturn(List.of(dto));

        List<UserDto> result = controller.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(service, times(1)).getAllUsers();

    }

}
