package com.indra.selecao.selecaojava.DTO;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity(name = "PRICE_HISTORY")
@Table(indexes = { 
		@Index(name = "IDX_CITY", columnList = "CITY"), 
		@Index(name = "IDX_LABEL", columnList = "LABEL"), 
		@Index(name = "IDX_REGION", columnList = "REGION"), 
		@Index(name = "IDX_DATE", columnList = "DATE") })
public class PriceHistoryDTO {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 3, message = "O tamanho mínimo para este campo é 1 e o máximo é 3")
	private String region;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 3, message = "O tamanho mínimo para este campo é 1 e o máximo é 3")
	private String state;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 50, message = "O tamanho mínimo para este campo é 3 e o máximo é 50")
	private String city;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 150, message = "O tamanho mínimo para este campo é 3 e o máximo é 150")
	private String merchant;
	
	@Column(nullable = false, name = "merchant_cnpj")
	@NotBlank
	@CNPJ(message = "O este campo deve conter exatamente 14 digitos numéricos")
	private String merchantCnpj;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 50, message = "O tamanho mínimo para este campo é 3 e o máximo é 50")
	private String product;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@Column(nullable = false)
	@PositiveOrZero
	private Double purchasePrice;
	
	@Column(nullable = false)
	@PositiveOrZero
	private Double salesPrice;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 50, message = "O tamanho máximo é 50")
	private String mesure;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 50, message = "O tamanho máximo é 50")
	private String label;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getMerchantCnpj() {
		return merchantCnpj;
	}

	public void setMerchantCnpj(String merchantCnpj) {
		this.merchantCnpj = merchantCnpj;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getMesure() {
		return mesure;
	}

	public void setMesure(String mesure) {
		this.mesure = mesure;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
