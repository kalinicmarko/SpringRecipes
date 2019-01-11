package com.springrecipes.mapper;

import com.springrecipes.dto.UnitOfMeasureDto;
import com.springrecipes.model.UnitOfMeasure;
import org.mapstruct.Mapper;

@Mapper
public interface UnitOfMeasureMapper {

    UnitOfMeasureDto unitOfMeasureToUnitOfMeasureDto(UnitOfMeasure unitOfMeasure);
}
