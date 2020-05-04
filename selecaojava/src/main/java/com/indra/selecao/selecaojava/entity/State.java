package com.indra.selecao.selecaojava.entity;

import javax.validation.constraints.Size;

public class State {

	@Size(min = 3, max = 50, message = "A quantidade de caracteres deve estar entre 3 e 50")
	private String name;
	
	private RegionEnum region;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RegionEnum getRegion() {
		return region;
	}

	public void setRegion(RegionEnum region) {
		this.region = region;
	}
	
}
