package com.loanapp.loanManagementSystem.service;

import com.loanapp.loanManagementSystem.dto.loan.HomeLoanDto;
import com.loanapp.loanManagementSystem.dto.loan.LoanDto;
import com.loanapp.loanManagementSystem.entities.loan.HomeLoan;
import com.loanapp.loanManagementSystem.entities.loan.Loan;
import com.loanapp.loanManagementSystem.entities.user.User;
import com.loanapp.loanManagementSystem.enums.LoanType;
import com.loanapp.loanManagementSystem.mapper.loan.*;
import com.loanapp.loanManagementSystem.repository.LoanRepository;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import com.loanapp.loanManagementSystem.service.loan.LoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestLoanService {
    @InjectMocks
   private LoanServiceImpl service;

    @Mock
    private UserRepository userRepository;

    @Mock
    LoanRepository loanRepository;

    @Mock
    private LoanMapper loanMapper;

    @Mock
    private HomeLoanMapper homeLoanMapper;

    UUID userID;
    User user;
    HomeLoan homeLoan;
    HomeLoanDto homeLoanDto;
    UUID loanID;
    LoanDto loanDto;

    @BeforeEach
    void setUp(){

        userID=UUID.randomUUID();
        loanID=UUID.randomUUID();

        user = new User();
        user.setId(userID);

        homeLoan = new HomeLoan();
        homeLoan.setLoanId(loanID);
        homeLoan.setUser(user);
        homeLoan.setLoanType(LoanType.HOME);


        homeLoanDto = new HomeLoanDto();
        homeLoanDto.setPropertyAddress("home");
        homeLoanDto.setPropertyAddress("pune");
        homeLoanDto.setLoanType(LoanType.HOME);
        homeLoanDto.setLoanAmount(20000.0);
        homeLoanDto.setInterestRate(7.8);
        homeLoanDto.setTenure(9);
    }

    @Test
    @DisplayName("Should create home loan successfully")
    void testCreateLoan(){
        when(userRepository.findById(userID)).thenReturn(Optional.ofNullable(user));
        when(homeLoanMapper.toEntity(any(HomeLoanDto.class))).thenReturn((homeLoan));
        when(loanRepository.save(any(Loan.class))).thenReturn(homeLoan);
        when(loanMapper.toDto(homeLoan)).thenReturn(homeLoanDto);

        LoanDto result=service.createLoan(userID,homeLoanDto);

        assertNotNull(result);
        assertEquals(LoanType.HOME,result.getLoanType());

        verify(userRepository, times(1)).findById(userID);
        verify(homeLoanMapper, times(1)).toEntity(any(HomeLoanDto.class));
        verify(loanRepository, times(1)).save(any(Loan.class));
        verify(loanMapper, times(1)).toDto(homeLoan);
    }

    @Test
    @DisplayName("Should update HOME loan successfully")
    void testUpdateLoan() {

        when(loanRepository.findByLoanIdAndIsActiveTrue(loanID))
                .thenReturn(Optional.of(homeLoan));

        when(loanRepository.save(any(Loan.class)))
                .thenReturn(homeLoan);

        when(loanMapper.toDto(homeLoan))
                .thenReturn(homeLoanDto);

        LoanDto result = service.updateLoan(loanID, homeLoanDto);

        assertNotNull(result);
        assertEquals(LoanType.HOME, result.getLoanType());

        verify(loanRepository).findByLoanIdAndIsActiveTrue(loanID);
        verify(homeLoanMapper).updateEntity(any(HomeLoanDto.class), any(HomeLoan.class));
        verify(loanRepository).save(any(Loan.class));
        verify(loanMapper).toDto(homeLoan);
    }

    @Test
    @DisplayName("Should soft delete loan")
    void testDeleteLoan() {

        when(loanRepository.findById(loanID))
                .thenReturn(Optional.of(homeLoan));

        service.deleteLoan(loanID);

        assertFalse(homeLoan.isActive());

        verify(loanRepository).findById(loanID);
        verify(loanRepository).save(homeLoan);
    }

    @Test
    @DisplayName("Should get loan by loanId")
    void testGetLoanById() {

        when(loanRepository.findById(loanID))
                .thenReturn(Optional.of(homeLoan));

        when(loanMapper.toDto(homeLoan))
                .thenReturn(homeLoanDto);

        LoanDto result = service.getLoanById(loanID);

        assertNotNull(result);
        assertEquals(LoanType.HOME, result.getLoanType());

        verify(loanRepository).findById(loanID);
        verify(loanMapper).toDto(homeLoan);
    }



}
