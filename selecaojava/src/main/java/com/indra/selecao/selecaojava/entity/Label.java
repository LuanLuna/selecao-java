package com.indra.selecao.selecaojava.entity;

import javax.validation.constraints.Size;

public class Label {
	
	@Size(min = 2, max = 50, message = "A quantidade de caracteres deve estar entre 2 e 50")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
