package com.example.demowithtests.dto;

import com.example.demowithtests.domain.PassportStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassportResponseDto implements Serializable {
    public String name;
    public LocalDate dateOfBirth;
    public String serialNumber;
    public LocalDate expireDate;
    public PassportStatus status;
    public PassportResponseDto prevPassport;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassportResponseDto that = (PassportResponseDto) o;
        return Objects.equals(name, that.name) && Objects.equals(dateOfBirth,
                that.dateOfBirth) && Objects.equals(serialNumber, that.serialNumber) && Objects.equals(
                expireDate, that.expireDate) && status == that.status && Objects.equals(prevPassport,
                that.prevPassport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, serialNumber, expireDate, status, prevPassport);
    }
}