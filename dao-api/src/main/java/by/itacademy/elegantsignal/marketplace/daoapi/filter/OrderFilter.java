package by.itacademy.elegantsignal.marketplace.daoapi.filter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OrderFilter extends AbstractFilter {

	private Integer userId;
	private final List<OrderStatus> orderStatus = new ArrayList<>();

	public Integer getUserId() {
		return this.userId;
	}

	public OrderFilter setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public List<OrderStatus> getOrderStatus() {
		return this.orderStatus;
	}

	public OrderFilter setOrderStatus(OrderStatus... orderStatus) {
		this.orderStatus.clear();
		this.orderStatus.addAll(Arrays.asList(orderStatus));
		return this;
	}
}
