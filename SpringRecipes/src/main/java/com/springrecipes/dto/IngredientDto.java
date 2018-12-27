package com.springrecipes.dto;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
public class IngredientDto {

	Long id;
	String name;
	BigDecimal amount;
	UnitOfMeasureDto uom;
}
