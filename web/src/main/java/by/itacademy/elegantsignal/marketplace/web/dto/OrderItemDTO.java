package by.itacademy.elegantsignal.marketplace.web.dto;

import java.math.BigDecimal;


public class OrderItemDTO {
	private Integer id;
	private String productTitle;
	private BigDecimal amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productName) {
		this.productTitle = productName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
