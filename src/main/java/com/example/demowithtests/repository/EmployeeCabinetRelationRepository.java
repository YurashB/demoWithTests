package com.example.demowithtests.repository;

import com.example.demowithtests.domain.EmployeeCabinetRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeCabinetRelationRepository extends JpaRepository<EmployeeCabinetRelation, Integer> {
    @Query(nativeQuery = true, value = " insert into employee_cabinet_relation " +
            "(cabinet_id, employee_id, is_active) values (?2, ?1, true)")
    void save(Integer employeeId, Integer cabinetId);
}
