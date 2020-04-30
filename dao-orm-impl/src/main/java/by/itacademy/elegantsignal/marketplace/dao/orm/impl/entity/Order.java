package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;

import java.util.*;
import javax.persistence.*;


@Entity(name = "Order")
@javax.persistence.Table(name = "`order`")
public class Order extends BaseEntity implements IOrder {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private IUser user;

	@Transient
	List<IOrderItem> orderItem = new ArrayList<>();

	@Column
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@Column
	private Date created;

	@Column
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
	public List<IOrderItem> getOrderItem() {
		return orderItem;
	}

	@Override
	public void setOrderItem(List<IOrderItem> orderItem) {
		this.orderItem = orderItem;
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

	@Override
	public void setStatus(String status) {
		this.status = OrderStatus.valueOf(status.toUpperCase());
	}

	@Override
	public OrderStatus getStatus() {
		return this.status;
	}
}
