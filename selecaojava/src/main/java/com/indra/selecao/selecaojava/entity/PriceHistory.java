package com.indra.selecao.selecaojava.entity;

import java.sql.Date;

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

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity(name = "PriceHistory")
public class PriceHistory {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Merchant merchant;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private City city;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "label_id", nullable = false)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY, hidden = true)
	private Label label;
	
	@Column(nullable = false)
	@Positive(message = "O valor deve ser maior ou igual a 0")
	private Double salesPrice;
	
	@Column(nullable = false)
	@Positive(message = "O valor deve ser maior ou igual a 0")
	private Double purchasePrice;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy/dd/MM")
	private Date date;
	
	@Transient
	@Positive(message = "O valor deve ser maior ou igual a 0")
	private Long productId;
	
	@Transient
	@Positive(message = "O valor deve ser maior ou igual a 0")
	private Long merchantId;
	
	@Transient
	@Positive(message = "O valor deve ser maior ou igual a 0")
	private Long cityId;
	
	@Transient
	@Positive(message = "O valor deve ser maior ou igual a 0")
	private Long labelId;

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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}
	
}
