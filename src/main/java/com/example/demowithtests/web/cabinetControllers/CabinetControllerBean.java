package com.example.demowithtests.web.cabinetControllers;

import com.example.demowithtests.dto.CabinetRequestDto;
import com.example.demowithtests.dto.CabinetResponseDto;
import com.example.demowithtests.service.CabinetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
public class CabinetControllerBean implements CabinetController {
    private final CabinetService cabinetService;

    @Override
    @PostMapping("/cabinets")
    @ResponseStatus(HttpStatus.CREATED)
    public CabinetResponseDto createCabinet(@RequestBody CabinetRequestDto cabinet) {
        return cabinetService.addCabinet(cabinet);
    }

    @Override
    @GetMapping("/cabinets/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CabinetResponseDto readCabinet(@PathVariable Integer id) {
        return cabinetService.getCabinet(id);
    }

    @Override
    @PatchMapping("/cabinets/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CabinetResponseDto updateCabinet(@PathVariable Integer id, @RequestBody CabinetRequestDto cabinet) {
        return cabinetService.updateCabinet(id, cabinet);
    }

    @Override
    @DeleteMapping("/cabinets/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCabinet(@PathVariable Integer id) {
        cabinetService.removeCabinet(id);
    }


    @Override
    @PostMapping("/cabinets/{cabinetId}/employee/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public void addEmployeeToCabinet( @PathVariable Integer cabinetId, @PathVariable Integer employeeId) {
         cabinetService.addEmployeeToCabinet(cabinetId,employeeId);
    }

    @Override
    public void deleteEmployeeFromCabinet(Integer cabinetId, Integer employeeId) {

    }
}
