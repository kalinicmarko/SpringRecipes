package com.springrecipes.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString(exclude = "recipes")
@EqualsAndHashCode(exclude = "recipes")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	/*@ManyToMany(mappedBy = "categories")
	private List<Recipe> recipes = new ArrayList<>();*/
}
