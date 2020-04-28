package com.indra.selecao.selecaojava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity(name = "City")
public class City {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	@Column(nullable = false)
	@Size(min = 3, max = 70, message = "A quantidade de caracteres deve estar entre 3 e 70")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "state_id", nullable = false)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private State state;
	
	@Transient
	@Positive(message = "O valor deve ser maior ou igual a 0")
	private Long stateId;

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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
}
