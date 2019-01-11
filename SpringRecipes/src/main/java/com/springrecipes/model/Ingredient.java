package com.springrecipes.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@ToString(exclude = {"recipe","unitOfMeasure"})
@EqualsAndHashCode(exclude = {"recipe","unitOfMeasure"})
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "amount")
	private BigDecimal amount;

	@ManyToOne
	private Recipe recipe;

	@ManyToOne
	private UnitOfMeasure unitOfMeasure;
}
