package com.example.demowithtests.service;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.dto.PassportRequestDto;
import com.example.demowithtests.dto.PassportResponseDto;
import com.example.demowithtests.repository.PassportRepository;
import com.example.demowithtests.util.config.mapstruct.PassportMapper;
import com.example.demowithtests.util.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class PassportServiceBean implements PassportService {
    private final PassportRepository passportRepository;
    private final PassportMapper passportMapper;

    @Override
    public PassportResponseDto create(PassportRequestDto passportRequestDto) {
        Passport passport = passportMapper.toModel(passportRequestDto);
        passport.setSerialNumber(getRandomSerialNumber());
        return passportMapper.toResponseDto(passportRepository.save(passport));
    }

    @Override
    public void removeById(Integer id) {
        passportRepository.deleteById(id);
    }

    @Override
    public PassportResponseDto updateById(Integer id, PassportRequestDto plane) {

        return passportMapper.toResponseDto(passportRepository.findById(id)
                .map(passport -> {
                    passport.setName(plane.getName());
                    passport.setDateOfBirth(plane.getDateOfBirth());
                    passport.setExpireDate(plane.getExpireDate());
                    return passportRepository.save(passport);
                })
                .orElseThrow(() -> new EntityNotFoundException("Passport not found with id = " + id)));
    }

    @Override
    public PassportResponseDto getById(Integer id) {
        return passportMapper.toResponseDto(passportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passport with id " + id + " not found")));
    }

    private String getRandomSerialNumber() {
        String serialNumber = "";
        Optional<Passport> passportWithGeneratedSerialNumber;
        do {
            serialNumber = RandomStringUtils.randomAlphabetic(4).toUpperCase() +
                    RandomStringUtils.randomNumeric(8);
            passportWithGeneratedSerialNumber = passportRepository.findBySerialNumber(serialNumber);
        } while (passportWithGeneratedSerialNumber.isPresent());
        return serialNumber;
    }
}