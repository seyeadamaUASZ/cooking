package com.sid.gl.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_recipe")
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long id;
	@Column(unique = true,length = 50)
	private String name;
	@Column(name = "decription")
   private String description;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
   private Category category;
     
	@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "ingredients")
   private Collection<String> ingredients=new ArrayList<>();
	
	@Embedded
	private FileCooking fileCooking;
	

   
}
