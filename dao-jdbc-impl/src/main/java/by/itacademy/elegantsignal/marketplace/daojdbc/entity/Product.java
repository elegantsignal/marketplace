package by.itacademy.elegantsignal.marketplace.daojdbc.entity;

import java.math.BigDecimal;
import java.util.Date;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;


public class Product extends BaseEntity implements IProduct {

	private IUser user;
	private ProductType type;
	private BigDecimal price;
	private Date created;
	private Date updated;

	@Override
	public IUser getUser() {
		return user;
	}

	@Override
	public void setUser(IUser user) {
		this.user = user;
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

	@Override
	public IBook getBook() {
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: getBook(); Timestamp: 1:46:43 AM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: getBook(); Timestamp: 1:46:43 AM");
		// return null;
	}

	@Override
	public void setBook(IBook book) {
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: setBook(); Timestamp: 1:46:43 AM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: setBook(); Timestamp: 1:46:43 AM");
		// 
	}

}
