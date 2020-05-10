package by.itacademy.elegantsignal.marketplace.daoapi.filter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OrderFilter extends AbstractFilter {

	private Integer userId;
	private final List<OrderStatus> orderStatus = new ArrayList<>();
	private final List<OrderStatus> notOrderStatusList = new ArrayList<>();

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

	public OrderFilter setOrderStatus(final OrderStatus... orderStatus) {
		this.orderStatus.clear();
		this.orderStatus.addAll(Arrays.asList(orderStatus));
		return this;
	}

	public OrderFilter setNotOrderStatus(final OrderStatus... statuses) {
		this.notOrderStatusList.addAll(Arrays.asList(statuses));
		return this;
	}

	public List<OrderStatus> getNotOrderStatus() {
		return notOrderStatusList;
	}
}
