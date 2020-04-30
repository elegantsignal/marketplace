package by.itacademy.elegantsignal.marketplace.daoapi.filter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;


public class OrderItemFilter extends AbstractFilter {

	private IOrder order;

	public IOrder getOrder() {
		return order;
	}

	public void setOrder(IOrder order) {
		this.order = order;
	}
}
