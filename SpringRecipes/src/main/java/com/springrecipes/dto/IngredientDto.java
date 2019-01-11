package com.springrecipes.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
public class IngredientDto {

	Long id;
	String name;
	BigDecimal amount;
	UnitOfMeasureDto unitOfMeasure;
}
