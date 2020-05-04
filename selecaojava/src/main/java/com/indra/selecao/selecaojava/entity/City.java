package com.indra.selecao.selecaojava.entity;

import javax.validation.constraints.Size;

public class City {
	
	@Size(min = 2, max = 70, message = "A quantidade de caracteres deve estar entre 2 e 70")
	private String name;
	
	private State state;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
}
