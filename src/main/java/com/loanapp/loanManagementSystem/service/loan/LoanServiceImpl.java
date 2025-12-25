package com.loanapp.loanManagementSystem.service.loan;

import com.loanapp.loanManagementSystem.dto.loan.*;
import com.loanapp.loanManagementSystem.entities.user.User;
import com.loanapp.loanManagementSystem.entities.loan.*;
import com.loanapp.loanManagementSystem.exception.ResourceNotFoundException;
import com.loanapp.loanManagementSystem.mapper.loan.*;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import com.loanapp.loanManagementSystem.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoanServiceImpl implements LoanService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LoanMapper loanMapper;

    @Autowired
    PersonalLoanMapper personalLoanMapper;

    @Autowired
    EducationLoanMapper educationLoanMapper;

    @Autowired
    BusinessMapper businessMapper;

    @Autowired
    HomeLoanMapper homeLoanMapper;

    @Override
    public LoanDto createLoan(UUID userId, LoanDto loanDto) {

        User user=userRepository.findById(userId).orElseThrow(()->{
            return new ResourceNotFoundException("User id not found");
        });

        if (loanDto.getLoanType() == null) {
            throw new RuntimeException("Loan type must not be null");
        }

        Loan loan;

        switch (loanDto.getLoanType()){
            case HOME:
                if (!(loanDto instanceof HomeLoanDto homeLoanDto)) {
                    throw new RuntimeException("Expected HomeLoanDto for HOME loan");
                }
                HomeLoan homeLoan = homeLoanMapper.toEntity(homeLoanDto);
                loan = homeLoan;
                break;

            case BUSINESS:
                if (!(loanDto instanceof BusinessLoanDto businessLoanDto)) {
                    throw new RuntimeException("Expected BusinessLoanDto");
                }

                BusinessLoan businessLoan= businessMapper.toEntity(businessLoanDto);
                loan = businessLoan;
                break;

            case PERSONAL:
                if (!(loanDto instanceof PersonalLoanDto personalLoanDto)) {
                    throw new RuntimeException("Expected PersonalLoanDto");
                }
                PersonalLoan personalLoan= personalLoanMapper.toEntity(personalLoanDto);
                loan = personalLoan;
                break;

            case EDUCATION:
                if (!(loanDto instanceof EducationLoanDto educationLoanDto)) {
                    throw new RuntimeException("Expected EducationLoanDto");
                }
                EducationLoan educationLoan = educationLoanMapper.toEntity(educationLoanDto);
                loan=educationLoan;
                break;

            default:
                throw new RuntimeException("Unsupported loan type");
        }


        loan.setUser(user);
        Loan saved=loanRepository.save(loan);
        return loanMapper.toDto(saved);
    }


    @Override
    public LoanDto getLoanById(UUID loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new ResourceNotFoundException("Loan id not found"));
        return loanMapper.toDto(loan);
    }

    @Override
    public List<LoanDto> getLoansByUserId(UUID userId) {
        List<Loan> loans=loanRepository.findByUserId(userId).orElseThrow(()->{
            return new ResourceNotFoundException("user id not found");
        });

        List<LoanDto> loanDtos=loans.stream().map(loan -> loanMapper.toDto(loan)).toList();

        return loanDtos;
    }

    @Override
    public void deleteLoan(UUID loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> {
            return new ResourceNotFoundException("Loan id not found");
        });

        loan.setActive(false);
        loanRepository.save(loan);
    }

    @Override
    public LoanDto updateLoan(UUID loanId, LoanDto loanDto) {
        Loan loan=loanRepository.findByLoanIdAndIsActiveTrue(loanId).orElseThrow(()->{
            return new ResourceNotFoundException("loan id not found");
        });

        if(!loan.getLoanType().equals(loanDto.getLoanType())){
            throw new RuntimeException("Loan type cannot be changed");
        }

        loan.setLoanAmount(loanDto.getLoanAmount());
        loan.setInterestRate(loanDto.getInterestRate());
        loan.setTenure(loanDto.getTenure());

        switch (loan.getLoanType()){
            case PERSONAL:
                if (!(loanDto instanceof PersonalLoanDto personalLoanDto)) {
                    throw new RuntimeException("Expected PersonalLoanDto");
                }
                personalLoanMapper.updateEntity(personalLoanDto, (PersonalLoan) loan);
                break;
            case EDUCATION:
                if (!(loanDto instanceof EducationLoanDto educationLoanDto)) {
                    throw new IllegalArgumentException("Expected EducationLoanDto");
                }
                educationLoanMapper.updateEntity(educationLoanDto, (EducationLoan) loan);
                break;
            case HOME:
                if (!(loanDto instanceof HomeLoanDto homeLoanDto)) {
                    throw new IllegalArgumentException("Expected HomeLoanDto");
                }
                homeLoanMapper.updateEntity(homeLoanDto, (HomeLoan) loan);
                break;
            case BUSINESS:
                if (!(loanDto instanceof BusinessLoanDto businessLoanDto)) {
                    throw new IllegalArgumentException("Expected BusinessLoanDto");
                }
                businessMapper.updateEntity(businessLoanDto, (BusinessLoan) loan);
                break;
            default:
                throw new RuntimeException("Unsupported loan type");
        }

       Loan updated= loanRepository.save(loan);
        return loanMapper.toDto(updated);
    }
}
