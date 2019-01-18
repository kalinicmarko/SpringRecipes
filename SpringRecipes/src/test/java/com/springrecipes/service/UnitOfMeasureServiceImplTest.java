package com.springrecipes.service;

import com.springrecipes.dto.UnitOfMeasureDto;
import com.springrecipes.mapper.UnitOfMeasureMapper;
import com.springrecipes.model.UnitOfMeasure;
import com.springrecipes.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureServiceImpl unitOfMeasureService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, UnitOfMeasureMapper.INSTANCE);
    }

    @Test
    public void findById() {

        //given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(1L);
        unitOfMeasure.setName("unitOfMeasure");

        Optional<UnitOfMeasure> unitOfMeasureOptional = Optional.of(unitOfMeasure);

        when(unitOfMeasureRepository.findById(anyLong())).thenReturn(unitOfMeasureOptional);

        //when
        UnitOfMeasureDto unitOfMeasureDto = unitOfMeasureService.findById(1L);

        //then
        assertNotNull("UnitOfMeasure returned null", unitOfMeasureDto);
        verify(unitOfMeasureRepository, times(1)).findById(anyLong());
    }
}