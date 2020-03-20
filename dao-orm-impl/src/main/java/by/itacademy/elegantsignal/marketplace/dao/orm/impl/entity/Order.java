package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import java.util.Date;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;


public class Order extends BaseEntity implements IOrder {

	private IUser user;
	private Date created;
	private Date updated;

	@Override
	public IUser getUser() {
		return user;
	}

	@Override
	public void setUser(final IUser user) {
		this.user = user;
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
