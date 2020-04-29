package com.indra.selecao.selecaojava.entity;

import java.sql.Date;

import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

public class PriceHistory {
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	private Product product;
	
	private Merchant merchant;
	
	private City city;
	
	private Label label;
	
	@Positive(message = "O valor deve ser maior ou igual a 0")
	private Double salesPrice;
	
	@Positive(message = "O valor deve ser maior ou igual a 0")
	private Double purchasePrice;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
