package com.example.demowithtests;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.repository.EmployeeRepository;
import com.example.demowithtests.service.EmployeeServiceBean;
import com.example.demowithtests.util.config.mapstruct.EmployeeMapper;
import com.example.demowithtests.util.config.mapstruct.PassportMapper;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceBean service;

    @Mock
    private EmployeeMapper employeeMapper;

    @Test
    public void whenSaveEmployee_shouldReturnEmployee() {
        Employee employee = new Employee();
        employee.setName("Mark");

        when(employeeRepository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);

        Employee created = service.create(employee);

        assertThat(created.getName()).isSameAs(employee.getName());
        verify(employeeRepository).save(employee);
    }

    @Test
    public void whenGivenId_shouldReturnEmployee_ifFound() {
        Employee employee = new Employee();
        employee.setId(88);

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.id = employee.getId();

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(employeeMapper.toDto(any(Employee.class))).thenReturn(employeeDto);

        EmployeeDto expected = service.getById(employee.getId());

        assertThat(expected.id).isSameAs(employee.getId());
        verify(employeeRepository).findById(employee.getId());
    }


    @Test(expected = EntityNotFoundException.class)
    public void should_throw_exception_when_employee_doesnt_exist() {
        Employee employee = new Employee();
        employee.setId(89);
        employee.setName("Mark");

        given(employeeRepository.findById(anyInt())).willReturn(Optional.empty());
        service.getById(employee.getId());
    }
}
