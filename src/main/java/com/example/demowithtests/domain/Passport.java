package com.example.demowithtests.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "passports")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Name may not be null")
    private String name;

    @NotNull(message = "Date of Birth may not be null")
    private LocalDate dateOfBirth;

    private String serialNumber;

    @NotNull(message = "Expire date may not be null")
    private LocalDate expireDate;

    @OneToOne(mappedBy = "passport")
    private Employee employee;
}
