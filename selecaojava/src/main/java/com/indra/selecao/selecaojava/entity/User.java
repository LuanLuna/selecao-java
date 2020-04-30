package com.indra.selecao.selecaojava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity(name = "User")
public class User{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank
	@Size(min = 8, max = 100, message = "O tamanho mínimo para este campo é 8 e o máximo é 100")
	private String name;
	
	@Column(nullable = false)
	@NotBlank
	@Size(min = 8, max = 24, message = "O tamanho mínimo para este campo é 8 e o máximo é 24")
	private String username;
	
	@Column(nullable = false)
	@ApiModelProperty(example = "user@company.com")
	@NotBlank
	@Email(message = "O email não está no formato correto! Tente algo no formato user@company.com")
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
