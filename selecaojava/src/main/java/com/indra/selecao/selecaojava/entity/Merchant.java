package com.indra.selecao.selecaojava.entity;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

public class Merchant {
	
	@Size(min = 2, max = 150, message = "A quantidade de caracteres deve estar entre 2 e 150")
	private String name;
	
	@CNPJ(message = "A quantidade de caracteres deve ser igual a 14")
	@Size(min = 14, max = 14, message = "A quantidade de caracteres deve ser igual a 14")
	private String cnpj;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}
