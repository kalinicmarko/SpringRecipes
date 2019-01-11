package com.springrecipes.service;

import com.springrecipes.dto.UnitOfMeasureDto;
import com.springrecipes.exceptions.UnitOfMeasureNotFoundException;
import com.springrecipes.mapper.UnitOfMeasureMapper;
import com.springrecipes.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureMapper unitOfMeasureMapper;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureMapper unitOfMeasureMapper) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureMapper = unitOfMeasureMapper;
    }

    @Override
    public UnitOfMeasureDto findById(Long id) {
        return unitOfMeasureRepository.findById(id)
                .map(unitOfMeasureMapper::unitOfMeasureToUnitOfMeasureDto)
                .orElseThrow(() -> new UnitOfMeasureNotFoundException("UnitOfMeasure not found"));
    }
}
