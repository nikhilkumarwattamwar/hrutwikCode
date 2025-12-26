package com.loanapp.loanManagementSystem.controller;

import com.loanapp.loanManagementSystem.contollers.loan.LoanController;
import com.loanapp.loanManagementSystem.dto.loan.HomeLoanDto;
import com.loanapp.loanManagementSystem.dto.loan.LoanDto;
import com.loanapp.loanManagementSystem.entities.loan.HomeLoan;
import com.loanapp.loanManagementSystem.entities.user.User;
import com.loanapp.loanManagementSystem.enums.LoanType;
import com.loanapp.loanManagementSystem.repository.LoanRepository;
import com.loanapp.loanManagementSystem.service.loan.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TestLoanController {

    @InjectMocks
    LoanController controller;

    @Mock
    LoanService service;

    UUID userID;
    UUID loanID;
    LoanDto loanDto;
    HomeLoanDto homeLoanDto;

    @BeforeEach
    void setUp() {
        userID = UUID.randomUUID();
        loanID = UUID.randomUUID();

        homeLoanDto = new HomeLoanDto();
        homeLoanDto.setPropertyAddress("Pune");
        homeLoanDto.setLoanType(LoanType.HOME);
        homeLoanDto.setLoanAmount(20000.0);
        homeLoanDto.setInterestRate(7.8);
        homeLoanDto.setTenure(9);

        loanDto = new LoanDto();
        loanDto.setLoanId(loanID);
        loanDto.setLoanType(LoanType.HOME);
        loanDto.setLoanAmount(20000.0);
        loanDto.setInterestRate(7.8);
        loanDto.setTenure(9);
    }

    @Test
    @DisplayName("createLoan : Should create loan successfully")
    void testCreateLoan() {
        when(service.createLoan(any(UUID.class), any())).thenReturn(loanDto);

        LoanDto result = controller.createLoan(userID, homeLoanDto);

        assertNotNull(result);
        assertEquals(LoanType.HOME, result.getLoanType());
        assertEquals(loanID, result.getLoanId());

        verify(service, times(1)).createLoan(userID, homeLoanDto);
    }

    @Test
    @DisplayName("getLoanById : Should return loan by ID")
    void testGetLoanById() {
        when(service.getLoanById(loanID)).thenReturn(loanDto);

        LoanDto result = controller.getLoanById(loanID);

        assertNotNull(result);
        assertEquals(loanID, result.getLoanId());
        verify(service, times(1)).getLoanById(loanID);
    }

    @Test
    @DisplayName("getLoansByUserId : Should return all loans for user")
    void testGetLoansByUserId() {
        when(service.getLoansByUserId(userID)).thenReturn(List.of(loanDto));

        List<LoanDto> result = controller.getLoansByUserId(userID);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(LoanType.HOME, result.get(0).getLoanType());
        verify(service, times(1)).getLoansByUserId(userID);
    }

    @Test
    @DisplayName("updateLoan : Should update loan successfully")
    void testUpdateLoan() {
        when(service.updateLoan(loanID, homeLoanDto)).thenReturn(loanDto);

        LoanDto result = controller.updateLoan(loanID, homeLoanDto);

        assertNotNull(result);
        assertEquals(loanID, result.getLoanId());
        assertEquals(LoanType.HOME, result.getLoanType());
        verify(service, times(1)).updateLoan(loanID, homeLoanDto);
    }


    @Test
    @DisplayName("deleteloan : Should soft delete loan successfully")
    void testDeleteLoan() {
        doNothing().when(service).deleteLoan(loanID);

        String result = controller.deleteLoan(loanID);

        assertEquals("Loan deleted successfully", result);
        verify(service, times(1)).deleteLoan(loanID);
    }





}
