package com.springrecipes.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springrecipes.model.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

	Optional<UnitOfMeasure> findByName(String name);
}
