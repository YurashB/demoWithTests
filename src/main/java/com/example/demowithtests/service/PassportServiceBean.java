package com.example.demowithtests.service;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.PassportStatus;
import com.example.demowithtests.dto.PassportRequestDto;
import com.example.demowithtests.dto.PassportResponseDto;
import com.example.demowithtests.repository.EmployeeRepository;
import com.example.demowithtests.repository.PassportRepository;
import com.example.demowithtests.util.config.mapstruct.PassportMapper;
import com.example.demowithtests.util.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class PassportServiceBean implements PassportService {
    private final PassportRepository passportRepository;
    private final PassportMapper passportMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public PassportResponseDto create(PassportRequestDto passportRequestDto) {
        Passport passport = passportMapper.toModel(passportRequestDto);
        passport.setSerialNumber(getRandomSerialNumber());
        return passportMapper.toResponseDto(passportRepository.save(passport));
    }

    @Override
    public void removeById(Integer id) {
        Passport passport = passportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passport with id " + id + " not found"));

        passport.setIsDeleted(Boolean.FALSE);
        passportRepository.save(passport);
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

    @Override
    public List<PassportResponseDto> generateFreePassports() {
        ArrayList<Passport> passports = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Passport passport = new Passport();
            passport.setSerialNumber(getRandomSerialNumber());
            passports.add(passport);
        }
        return passportMapper.toListResponseDto(passportRepository.saveAll(passports));
    }

    @Override
    public Passport getFree() {
        return passportRepository.findAll().stream()
                .filter(e -> (e.getIsDeleted() == null || !e.getIsDeleted()))
                .filter(Passport::getIsFree)
                .findFirst().orElseThrow(() -> (new ResourceNotFoundException("Free Passport entity not found")));
    }

    @Override
    public PassportResponseDto addNewActivePassport(Integer prevPassportId, String prevPassportStatus) {
        Passport oldPassport = passportRepository.findById(prevPassportId)
                .filter(passport -> !passport.getIsDeleted())
                .filter(passport -> !passport.getIsFree())
                .orElseThrow(() -> new EntityNotFoundException("Passport not found with id = " + prevPassportId));

        PassportStatus oldPassportStatus = PassportStatus.valueOf(prevPassportStatus.toUpperCase());

        oldPassport.setStatus(oldPassportStatus);
        oldPassport.setIsDeleted(Boolean.TRUE);
        oldPassport.setIsFree(Boolean.FALSE);

        Passport newPassport = new Passport();
        newPassport.setIsFree(Boolean.FALSE);
        newPassport.setName(oldPassport.getEmployee().getName());
        newPassport.setDateOfBirth(oldPassport.getDateOfBirth());
        newPassport.setPrevPassport(oldPassport);
        newPassport.setEmployee(oldPassport.getEmployee());
        newPassport.setStatus(PassportStatus.ACTIVE);
        newPassport.setSerialNumber(oldPassport.getSerialNumber());

        passportRepository.save(oldPassport);
        passportRepository.save(newPassport);
        employeeRepository.addPassportToEmployee(oldPassport.getEmployee().getId(), newPassport);
        return passportMapper.toResponseDto(newPassport);
    }
}