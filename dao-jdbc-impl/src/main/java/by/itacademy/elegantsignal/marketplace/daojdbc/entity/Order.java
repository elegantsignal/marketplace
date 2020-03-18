package by.itacademy.elegantsignal.marketplace.daojdbc.entity;

import java.util.Date;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;


public class Order extends BaseEntity implements IOrder {

	private IUserAccount userAccount;
	private Date created;
	private Date updated;

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(final IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(final Date created) {
		this.created = created;
	}

	@Override
	public Date getUpdated() {
		return updated;
	}

	@Override
	public void setUpdated(final Date updated) {
		this.updated = updated;
	}
}
