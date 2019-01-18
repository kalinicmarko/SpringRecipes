package com.springrecipes.mapper;

import com.springrecipes.dto.UnitOfMeasureDto;
import com.springrecipes.model.UnitOfMeasure;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UnitOfMeasureMapper {

    UnitOfMeasureMapper INSTANCE = Mappers.getMapper(UnitOfMeasureMapper.class);
    UnitOfMeasureDto unitOfMeasureToUnitOfMeasureDto(UnitOfMeasure unitOfMeasure);
}
