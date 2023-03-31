package com.example.demowithtests.util.config.mapstruct;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.dto.PassportRequestDto;
import com.example.demowithtests.dto.PassportResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassportMapper {
    Passport toModel(PassportRequestDto dto);

    PassportResponseDto toResponseDto(Passport passport);
}
