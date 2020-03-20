package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import java.util.Date;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILike;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;



public class Like extends BaseEntity implements ILike {

	private IUser user;
	private IProduct product;
	private Date created;

	@Override
	public IUser getUser() {
		return user;	
	}

	@Override
	public void setUser(IUser user) {
		this.user = user;
	}

	@Override
	public IProduct getProduct() {
		return product;
	}

	@Override
	public void setProduct(IProduct product) {
		this.product = product;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(Date created) {
		this.created = created;
	}



}
