package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import java.math.BigDecimal;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;


public class OrderItem extends BaseEntity implements IOrderItem {

	private IOrder order;
	private IProduct product;
	private BigDecimal amount;

	@Override
	public IOrder getOrder() {
		return this.order;
	}

	@Override
	public void setOrder(final IOrder order) {
		this.order = order;
	}

	@Override
	public IProduct getProduct() {
		return this.product;
	}

	@Override
	public void setProduct(final IProduct product) {
		this.product = product;
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
