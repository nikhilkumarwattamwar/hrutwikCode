package com.loanapp.loanManagementSystem.service.user;

import com.loanapp.loanManagementSystem.dto.user.EducationDto;
import com.loanapp.loanManagementSystem.entities.user.EducationDetails;
import com.loanapp.loanManagementSystem.entities.user.User;
import com.loanapp.loanManagementSystem.mapper.user.EducationMapper;
import com.loanapp.loanManagementSystem.repository.EducationRepository;
import com.loanapp.loanManagementSystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EducationMapper mapper;

    @Transactional
    @Override
    public List<EducationDto> saveEducationDetail(List<EducationDto> dto, UUID userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> {
            return new RuntimeException("User ID not found");
        });

        List<EducationDetails> detailsList = new ArrayList<>();
        for (EducationDto educationDto : dto) {
            EducationDetails details = mapper.toEntity(educationDto);
            details.setUser(user);
            user.setEducationDetails(details);
            detailsList.add(details);
        }

        List<EducationDetails> savedList = educationRepository.saveAll(detailsList);


        return mapper.toDtoList(savedList);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EducationDto> getAllEducationDetail() {

        List<EducationDetails> list = educationRepository.findAll();

        return list.stream().map(details -> mapper.toDto(details)).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<EducationDto> getEducationDetailById(UUID userId) {

        List<EducationDetails> details = educationRepository.findAllByUserId(userId).orElseThrow(() -> {
            return new RuntimeException("User ID not found");
        });

        return mapper.toDtoList(details);
    }

    @Transactional
    public List<EducationDto> updateEducationDetail(List<EducationDto> dtoList, UUID userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> {
            return new RuntimeException("User ID not found");
        });


        for (EducationDto dto1 : dtoList) {
            EducationDetails existing = educationRepository.findByUserIdAndExamPassed(userId, dto1.getExamPassed()).orElse(null);

            if (existing != null) {
                mapper.updateFromDtoToEntity(dto1, existing);
            } else {
                EducationDetails details = mapper.toEntity(dto1);
                details.setUser(user);
                user.getEducationDetailsList().add(details);
            }
        }

        userRepository.save(user);

        return mapper.toDtoList(user.getEducationDetailsList());
    }

    @Transactional
    @Override
    public void deleteAllEducationByUserId(UUID userId) {

        List<EducationDetails> list = educationRepository.findAllByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No education records found"));

        educationRepository.deleteAll(list);
    }

}
