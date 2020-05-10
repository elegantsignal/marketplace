package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;


@Entity
public class OrderItem extends BaseEntity implements IOrderItem {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
	private IOrder order;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
	private IProduct product;
	
	@Column
	private BigDecimal amount;

	@Override
	public IOrder getOrder() {
		return this.order;
	}

	@Override
	public OrderItem setOrder(final IOrder order) {
		this.order = order;
		return this;
	}

	@Override
	public IProduct getProduct() {
		return this.product;
	}

	@Override
	public IOrderItem setProduct(final IProduct product) {
		this.product = product;
		return this;
	}

	@Override
	public BigDecimal getAmount() {
		return this.amount;
	}

	@Override
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}
}
