package com.example.demowithtests.domain;

import com.example.demowithtests.util.annotation.EmployeeCountry;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @EmployeeCountry
    private String country;

    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Set<Address> addresses = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String identifier;

    private Boolean isTest = Boolean.FALSE;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Set<Photo> photos = new HashSet<>();

}
