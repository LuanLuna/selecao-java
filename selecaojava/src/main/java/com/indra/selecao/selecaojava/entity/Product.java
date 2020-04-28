package com.indra.selecao.selecaojava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity(name = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	@Column(nullable = false)
	@Size(min = 3, max = 50, message = "A quantidade de caracteres deve estar entre 3 e 50")
	private String name;
	
	@Column(nullable = false)
	@Size(min = 5, max = 50, message = "A quantidade de caracteres deve estar entre 5 e 50")
	private String measure;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}
	
}