package com.example.demowithtests.web.passportControllers;

import com.example.demowithtests.dto.PassportRequestDto;
import com.example.demowithtests.dto.PassportResponseDto;

import java.util.List;

public interface PassportController {
    PassportResponseDto savePassport(PassportRequestDto requestForSave);

    PassportResponseDto refreshPassport(Integer id, PassportRequestDto requestForSave);

    PassportResponseDto getPassportById(Integer id);

    void removePassportById(Integer id);

    List<PassportResponseDto> generateFiveFreePassports();

    PassportResponseDto addNewActivePassport(Integer prevPassportId, String prevPassportStatus);

}
