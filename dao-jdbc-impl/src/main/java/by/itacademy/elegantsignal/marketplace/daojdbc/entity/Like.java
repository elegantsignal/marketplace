package by.itacademy.elegantsignal.marketplace.daojdbc.entity;

import java.util.Date;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILike;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;



public class Like extends BaseEntity implements ILike {

	private IUserAccount userAccount;
	private IProduct product;
	private Date created;

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;	
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
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
