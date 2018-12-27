package com.springrecipes.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
public class UnitOfMeasureDto {
	Long id;
	String name;
}
