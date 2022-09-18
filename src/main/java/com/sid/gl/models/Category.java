package com.sid.gl.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
	@NotEmpty
  private String name;
}
