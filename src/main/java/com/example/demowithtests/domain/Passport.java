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
@ToString
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private LocalDate dateOfBirth;

    private String serialNumber;

    private LocalDate expireDate = LocalDate.now().plusYears(5);

    @OneToOne(mappedBy = "passport")
    private Employee employee;

    private Boolean isFree = Boolean.TRUE;

    private Boolean isDeleted = Boolean.FALSE;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PassportStatus status = PassportStatus.ACTIVE;

    @OneToOne
    @JoinColumn(name = "prev_passport_id", referencedColumnName = "id")
    private Passport prevPassport;
}
