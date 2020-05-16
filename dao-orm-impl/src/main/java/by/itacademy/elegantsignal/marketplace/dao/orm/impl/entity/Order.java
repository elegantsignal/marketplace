package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity(name = "Order")
@javax.persistence.Table(name = "`order`")
public class Order extends BaseEntity implements IOrder {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private IUser user;

	@Transient
	List<IOrderItem> orderItemList = new ArrayList<>();

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
	public IOrder setUser(final IUser user) {
		this.user = user;
		return this;
	}

	@Override
	public List<IOrderItem> getOrderItems() {
		return orderItemList;
	}

	@Override
	public IOrder setOrderItemList(final List<IOrderItem> orderItemList) {
		this.orderItemList = orderItemList;
		return this;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public IOrder setCreated(final Date created) {
		this.created = created;
		return this;
	}

	@Override
	public Date getUpdated() {
		return updated;
	}

	@Override
	public IOrder setUpdated(final Date updated) {
		this.updated = updated;
		return this;
	}

	@Override
	public IOrder setStatus(final OrderStatus status) {
		this.status = status;
		return this;
	}

	@Override
	public OrderStatus getStatus() {
		return status;
	}

	@Override public IOrder addOrderItem(final IOrderItem orderItem) {
		orderItemList.add(orderItem);
		return this;
	}
}
