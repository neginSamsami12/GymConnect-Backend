package com.gymconnect.classes.mapper;

import com.gymconnect.classes.dto.ClassCreateRequest;
import com.gymconnect.classes.dto.ClassResponse;
import com.gymconnect.classes.entity.Class;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trainer", ignore = true)
    @Mapping(target = "imageUrl", ignore = true)
    Class toEntity(ClassCreateRequest request);

    @Mapping(target = "trainerId", source = "trainer.id")
    ClassResponse toResponse(Class entity);

    List<ClassResponse> toResponseList(List<Class> entities);
}
