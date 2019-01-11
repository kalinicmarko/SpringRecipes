package com.springrecipes.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "description")
	@Lob
	private String description;

}
