package by.itacademy.elegantsignal.marketplace.daoapi.filter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Getter @Setter @Accessors(chain = true)
public class OrderItemFilter extends AbstractFilter {

	private Integer id;
	private Integer userId;
	private Integer productOwnerId;
	private final List<Integer> orderIds = new ArrayList<>();
	private final List<OrderStatus> orderStatusList = new ArrayList<>();
	private final List<OrderStatus> excludeOrderStatusList = new ArrayList<>();

	public OrderItemFilter setOrderIds(final List<IOrder> orders) {
		return setOrderIds(orders.toArray(new IOrder[0]));
	}

	public OrderItemFilter setOrderIds(final IOrder... orders) {
		for (final IOrder order : orders) {
			orderIds.add(order.getId());
		}
		return this;
	}

	public OrderItemFilter setExcludeOrderStatusList(final OrderStatus... excludeOrderStatus) {
		excludeOrderStatusList.addAll(Arrays.asList(excludeOrderStatus));
		return this;
	}

	public OrderItemFilter setOrderStarusList(final OrderStatus... statuses) {
		orderStatusList.addAll(Arrays.asList(statuses));
		return this;
	}
}
