package com.example.demowithtests.service;

import com.example.demowithtests.domain.Cabinet;
import com.example.demowithtests.domain.EmployeeCabinetRelation;
import com.example.demowithtests.dto.CabinetRequestDto;
import com.example.demowithtests.dto.CabinetResponseDto;
import com.example.demowithtests.repository.CabinetRepository;
import com.example.demowithtests.repository.EmployeeCabinetRelationRepository;
import com.example.demowithtests.repository.EmployeeRepository;
import com.example.demowithtests.util.config.mapstruct.CabinetMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CabinetServiceBean implements CabinetService {

    private final CabinetRepository cabinetRepository;
    private final EmployeeCabinetRelationRepository employeeCabinetRelationRepository;
    private final CabinetMapper cabinetMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public CabinetResponseDto addCabinet(CabinetRequestDto cabinet) {
        Cabinet cabinetModel = cabinetMapper.toModel(cabinet);
        Cabinet save = cabinetRepository.save(cabinetModel);
        return cabinetMapper.toDto(save);
    }

    @Override
    public CabinetResponseDto getCabinet(Integer id) {
        Cabinet cabinet = cabinetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cabinet not found with id = " + id));

        if (cabinet.getIsDeleted()) {
            throw new EntityNotFoundException("Cabinet not found with id = " + id);
        }

        cabinet.getEmployees().removeIf(employeeCabinetRelation -> !employeeCabinetRelation.getIsActive());

        return cabinetMapper.toDto(cabinet);
    }

    @Override
    public CabinetResponseDto updateCabinet(Integer id, CabinetRequestDto cabinet) {
        return cabinetMapper.toDto(
                cabinetRepository.findById(id)
                        .filter(e -> !e.getIsDeleted())
                        .map(entity -> {
                            entity.setName(cabinet.name);
                            entity.setCapacity(!Objects.isNull(cabinet.capacity) ? cabinet.capacity : entity.getCapacity());
                            return cabinetRepository.save(entity);
                        })
                        .orElseThrow(() -> new EntityNotFoundException("Cabinet not found with id = " + id)));
    }

    @Override
    public void removeCabinet(Integer id) {
        Cabinet cabinet = cabinetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cabinet not found with id = " + id));

        cabinet.setIsDeleted(Boolean.TRUE);
        cabinetRepository.save(cabinet);
    }

    @Override
    public void addEmployeeToCabinet(Integer cabinetId, Integer employeeId) {
        Cabinet cabinet = cabinetRepository.findById(cabinetId)
                .orElseThrow(() -> new EntityNotFoundException("Cabinet not found with id = " + cabinetId));

        if (cabinet.getIsDeleted()) {
            throw new EntityNotFoundException("Cabinet not found with id = " + cabinetId);
        }

        var employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + employeeId));


        if (employee.getIsDeleted() == null) {
            employee.setIsDeleted(Boolean.TRUE);
        } else if (employee.getIsDeleted()) {
            throw new EntityNotFoundException("User try to access to deleted employee with id: " + employee
                    + " where isDeleted field is true");
        }

        employeeCabinetRelationRepository
                .save(employeeId, cabinetId);
    }

    @Override
    public void deleteEmployeeFromCabinet(Integer cabinetId, Integer employeeId) {

    }
}
