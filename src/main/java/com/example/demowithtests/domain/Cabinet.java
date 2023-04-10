package com.example.demowithtests.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cabinets")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Cabinet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    private Integer capacity = 1;

    private Boolean isDeleted = Boolean.FALSE;

    @OneToMany(mappedBy = "cabinet")
    private Set<EmployeeCabinetRelation> employees = new HashSet<>();
}
