package com.example.demowithtests.service;

import com.example.demowithtests.domain.Cabinet;
import com.example.demowithtests.dto.CabinetRequestDto;
import com.example.demowithtests.dto.CabinetResponseDto;

public interface CabinetService {
    CabinetResponseDto addCabinet(CabinetRequestDto cabinet);

    CabinetResponseDto getCabinet(Integer id);

    CabinetResponseDto updateCabinet(Integer id, CabinetRequestDto cabinet);

    void removeCabinet(Integer id);

    void addEmployeeToCabinet(Integer cabinetId, Integer employeeId);

    void deleteEmployeeFromCabinet(Integer cabinetId, Integer employeeId);
}
