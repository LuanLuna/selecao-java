package com.indra.selecao.selecaojava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity(name = "Merchant")
public class Merchant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	@Column(nullable = false)
	@Size(min = 5, max = 150, message = "A quantidade de caracteres deve estar entre 5 e 150")
	private String name;
	
	@Column(nullable = false)
	@CNPJ(message = "A quantidade de caracteres deve ser igual a 14")
	@Size(min = 14, max = 14, message = "A quantidade de caracteres deve ser igual a 14")
	private String cnpj;

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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}
