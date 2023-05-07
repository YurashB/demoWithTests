package com.example.demowithtests.util.config.mapstruct;

import com.example.demowithtests.domain.Address;
import com.example.demowithtests.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toDto(Address address);

    Address toModel(AddressDto addressDto);

}
