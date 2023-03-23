package com.example.demowithtests.util.config.mapstruct;

import com.example.demowithtests.domain.Photo;
import com.example.demowithtests.dto.PhotoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = PhotoMapper.class)
public interface PhotoMapper {
    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);

    PhotoDto toDto(Photo photo);

    Photo toModel(PhotoDto photoDto);

}
