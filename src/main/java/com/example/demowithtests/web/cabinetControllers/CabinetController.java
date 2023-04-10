package com.example.demowithtests.web.cabinetControllers;

import com.example.demowithtests.dto.CabinetRequestDto;
import com.example.demowithtests.dto.CabinetResponseDto;

public interface CabinetController {

    CabinetResponseDto createCabinet(CabinetRequestDto cabinet);

    CabinetResponseDto readCabinet(Integer id);

    CabinetResponseDto updateCabinet(Integer id, CabinetRequestDto cabinet);

    void deleteCabinet(Integer id);

    void addEmployeeToCabinet(Integer cabinetId, Integer employeeId);

    void deleteEmployeeFromCabinet(Integer cabinetId, Integer employeeId);
}
