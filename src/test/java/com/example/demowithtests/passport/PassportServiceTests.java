package com.example.demowithtests.passport;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.PassportStatus;
import com.example.demowithtests.dto.PassportRequestDto;
import com.example.demowithtests.dto.PassportResponseDto;
import com.example.demowithtests.repository.PassportRepository;
import com.example.demowithtests.service.PassportServiceBean;
import com.example.demowithtests.util.config.mapstruct.PassportMapper;

@ExtendWith(MockitoExtension.class)
public class PassportServiceTests {

    @Mock
    private PassportRepository passportRepository;

    @InjectMocks
    private PassportServiceBean passportService;

    @Mock
    private PassportMapper passportMapper;

    private Passport passport;
    private PassportRequestDto passportRequestDto;
    private PassportResponseDto passportResponseDto;

    @BeforeEach
    public void setup() {
        passport = new Passport(1, "Mark", LocalDate.now(), null, LocalDate.now().plusYears(5),
                new Employee(), Boolean.TRUE, Boolean.FALSE, PassportStatus.ACTIVE, null);

        passportRequestDto = new PassportRequestDto();
        passportRequestDto.name = passport.getName();
        passportRequestDto.dateOfBirth = passport.getDateOfBirth();
        passportRequestDto.expireDate = passport.getExpireDate();
        passportRequestDto.status = passport.getStatus();

        passportResponseDto = new PassportResponseDto();
        passportResponseDto.name = passport.getName();
        passportResponseDto.status = passport.getStatus();
        passportResponseDto.dateOfBirth = passport.getDateOfBirth();
        passportResponseDto.expireDate = passport.getExpireDate();
        passportResponseDto.serialNumber = passport.getSerialNumber();
    }

    @Test
    public void givenPassportRequestDtoShouldReturnPassportResponseDto() {
        when(passportRepository.save(any(Passport.class))).thenReturn(passport);
        when(passportMapper.toModel(eq(passportRequestDto))).thenReturn(passport);
        when(passportMapper.toResponseDto(eq(passport))).thenReturn(passportResponseDto);

        PassportResponseDto passportResponseDtoFromService = passportService.create(passportRequestDto);

        assertThat(passportResponseDtoFromService.name).isEqualTo(passportResponseDto.name);
        verify(passportRepository).save(passport);
    }

    @Test
    public void generateRandomSerialNumberTest() {

    }

}
