package com.springrecipes.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "prop_time")
	private Integer propTime;

	@Column(name = "cook_time")
	private Integer cookTime;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Note note;
	
	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;
	
	@ManyToMany
	@JoinTable(name = "recipe_category",
		joinColumns = @JoinColumn(name = "recipe_id"), 
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(mappedBy="recipe", cascade=CascadeType.ALL)
	private Set<Ingredient> ingredients = new HashSet<>();
}
