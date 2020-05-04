package com.indra.selecao.selecaojava.entity;

import javax.validation.constraints.Size;

public class Product {
	
	@Size(min = 3, max = 50, message = "A quantidade de caracteres deve estar entre 3 e 50")
	private String name;
	
	@Size(min = 5, max = 50, message = "A quantidade de caracteres deve estar entre 5 e 50")
	private String measure;

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
