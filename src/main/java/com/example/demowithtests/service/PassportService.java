package com.example.demowithtests.service;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.dto.PassportRequestDto;
import com.example.demowithtests.dto.PassportResponseDto;

import java.util.List;

public interface PassportService {
    PassportResponseDto create(PassportRequestDto passportRequestDto);

    void removeById(Integer id);

    PassportResponseDto updateById(Integer id, PassportRequestDto plane);

    PassportResponseDto getById(Integer id);
}
