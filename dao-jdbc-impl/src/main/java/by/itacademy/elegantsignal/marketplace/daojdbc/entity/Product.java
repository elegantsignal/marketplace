package by.itacademy.elegantsignal.marketplace.daojdbc.entity;

import java.math.BigDecimal;
import java.util.Date;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;


public class Product extends BaseEntity implements IProduct {

	private IUserAccount userAccount;
	private ProductType type;
	private BigDecimal price;
	private Date created;
	private Date updated;

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public ProductType getType() {
		return type;
	}

	@Override
	public void setType(ProductType type) {
		this.type = type;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public Date getUpdated() {
		return updated;
	}

	@Override
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
