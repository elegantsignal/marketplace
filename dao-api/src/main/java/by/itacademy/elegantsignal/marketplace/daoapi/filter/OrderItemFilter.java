package by.itacademy.elegantsignal.marketplace.daoapi.filter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OrderItemFilter extends AbstractFilter {

	private Integer id;
	private Integer userId;
	private Integer productOwnerId;
	private final List<Integer> orderIds = new ArrayList<>();
	private final List<OrderStatus> excludeOrderStatusList = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public OrderItemFilter setId(final Integer id) {
		this.id = id;
		return this;
	}

	public List<Integer> getOrderIds() {
		return orderIds;
	}

	public OrderItemFilter setOrderIds(final IOrder... orders) {
		for (final IOrder order : orders) {
			orderIds.add(order.getId());
		}
		return this;
	}

	public OrderItemFilter setOrderIds(final List<IOrder> orders) {
		return setOrderIds(orders.toArray(new IOrder[0]));
	}

	public Integer getUserId() {
		return userId;
	}

	public OrderItemFilter setUserId(final Integer userId) {
		this.userId = userId;
		return this;
	}

	public List<OrderStatus> getExcludeOrderStatusList() {
		return excludeOrderStatusList;
	}

	public OrderItemFilter setExcludeOrderStatusList(final OrderStatus... excludeOrderStatus) {
		excludeOrderStatusList.addAll(Arrays.asList(excludeOrderStatus));
		return this;
	}

	public OrderItemFilter setProductOwnerId(final Integer productOwnerId) {
		this.productOwnerId = productOwnerId;
		return this;
	}

	public Integer getProductOwnerId() {
		return productOwnerId;
	}
}
