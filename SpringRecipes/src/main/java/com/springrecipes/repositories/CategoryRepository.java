package com.springrecipes.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springrecipes.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
