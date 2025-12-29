package com.loanapp.loanManagementSystem.service;

import com.loanapp.loanManagementSystem.dto.loan.HomeLoanDto;
import com.loanapp.loanManagementSystem.dto.loan.LoanDto;
import com.loanapp.loanManagementSystem.entities.loan.HomeLoan;
import com.loanapp.loanManagementSystem.entities.loan.Loan;
import com.loanapp.loanManagementSystem.entities.user.User;
import com.loanapp.loanManagementSystem.enums.LoanType;
import com.loanapp.loanManagementSystem.exception.ResourceNotFoundException;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {
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
    @DisplayName("User not found")
    void testUserNotFound(){
        UUID random=UUID.randomUUID();
        when(userRepository.findById(random)).thenReturn(Optional.empty());
         assertThrows(ResourceNotFoundException.class,()->service.createLoan(random,loanDto));

    }

    @Test
    @DisplayName("Throw exception when loan type is null")
    void testLoanTypeNull(){
        UUID random = UUID.randomUUID();
        User user = new User();

        LoanDto dto = new HomeLoanDto();

        when(userRepository.findById(random))
                .thenReturn(Optional.of(user));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.createLoan(random, dto));

        assertEquals("Loan type must not be null", ex.getMessage());
    }

    @Test
    @DisplayName("Unsupported loan type")
    void testUnsupportedLoanType(){
        UUID userId = UUID.randomUUID();
       LoanDto dto= new HomeLoanDto();
       dto.setLoanType(null);
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(user));

        assertThrows(RuntimeException.class,
                () -> service.createLoan(userId, dto));
    }


    @Test
    @DisplayName("get list of all loan by user id")
    void testGetAllLoanByUserId(){

        List<Loan> loans=List.of(homeLoan);

        when(loanRepository.findByUserId(userID)).thenReturn(Optional.of(loans));
        when(loanMapper.toDto(homeLoan)).thenReturn(homeLoanDto);

        List<LoanDto> result = service.getLoansByUserId(userID);

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(loanRepository).findByUserId(userID);
        verify(loanMapper).toDto(homeLoan);
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
    @DisplayName("Throw exception when loan details are not found using loanId")
    void testLoanNotFound(){
        when(loanRepository.findByLoanIdAndIsActiveTrue(loanID)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,()->service.updateLoan(loanID,loanDto));
    }

    @Test
    @DisplayName("Cannot update loan type")
    void testChangeLoanType(){
        homeLoan.setLoanType(LoanType.BUSINESS);
        when(loanRepository.findByLoanIdAndIsActiveTrue(loanID)).thenReturn(Optional.ofNullable(homeLoan));

        RuntimeException ex=assertThrows(RuntimeException.class,()->service.updateLoan(loanID,homeLoanDto));
        assertEquals("Loan type cannot be changed",ex.getMessage());
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
