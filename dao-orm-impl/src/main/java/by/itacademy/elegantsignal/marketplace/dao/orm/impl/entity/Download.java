package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IDownload;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;


@Entity
public class Download extends BaseEntity implements IDownload {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = OrderItem.class)
	IOrderItem orderItem;

	@Column
	String token;

	@Column(updatable = false)
	Date created;

	@Override public IOrderItem getOrderItem() {
		return orderItem;
	}

	@Override public void setOrderItem(final IOrderItem orderItem) {
		this.orderItem = orderItem;
	}

	@Override public String getToken() {
		return token;
	}

	@Override public void setToken(final String token) {
		this.token = token;
	}

	@Override public Date getCreated() {
		return created;
	}

	@Override public void setCreated(final Date created) {
		this.created = created;
	}
}
