package com.example.demowithtests.passport;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.PassportStatus;
import com.example.demowithtests.repository.PassportRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Test of Passport Repository")
public class PassportRepositoryTest {

    @Autowired
    private PassportRepository passportRepository;

    private Passport passport = new Passport();

    @BeforeEach
    public void setup() {
        passport = new Passport(1, "Mark", LocalDate.now(), "XX0000XX", LocalDate.now().plusYears(5), null, Boolean.TRUE, Boolean.FALSE, PassportStatus.ACTIVE, null);
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void savePassportTest() {
        passportRepository.save(passport);

        Assertions.assertThat(passport.getId()).isGreaterThan(0);
        Assertions.assertThat(passport.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void getPassportBySerialNumberTest() {
        Passport passportFromDb = passportRepository.findBySerialNumber(passport.getSerialNumber()).orElseThrow();
        Assertions.assertThat(passportFromDb.getId()).isEqualTo(1);

    }

    @Test
    @Order(2)
    public void getListOfPassportsTest() {

        List<Passport> passports = passportRepository.findAll();
        System.out.println(passports);

        Assertions.assertThat(passports.size()).isEqualTo(1);

    }
}
