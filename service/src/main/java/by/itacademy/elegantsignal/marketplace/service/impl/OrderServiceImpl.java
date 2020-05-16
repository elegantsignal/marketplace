package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.IOrderDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.service.IOrderItemService;
import by.itacademy.elegantsignal.marketplace.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired private IOrderDao orderDao;
	@Autowired private IOrderItemService orderItemService;

	@Override
	public IOrder createEntity() {
		return orderDao.createEntity();
	}

	@Override
	public void save(final IOrder entity) {
		final Date modifiedOn = new Date();
		entity.setUpdated(modifiedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifiedOn);
			orderDao.insert(entity);
		} else {
			orderDao.update(entity);
		}
	}

	@Override
	public IOrder get(final Integer id) {
		return orderDao.get(id);
	}

	@Override
	public void delete(final Integer id) {
		orderDao.delete(id);
	}

	@Override
	public void deleteAll() {
		orderDao.deleteAll();
	}

	@Override
	public List<IOrder> getAll() {
		return orderDao.selectAll();
	}

	//	@Override
	//	public List<IOrder> getOrdersByUserId(final Integer userId) {
	//		final OrderFilter orderFilter = new OrderFilter().setUserId(userId).setNotOrderStatus(OrderStatus.CART);
	//		return orderDao.find(orderFilter);
	//	}

	// TODO: This is crutch(костыль), replace this with something normal
	@Override public List<IOrder> getOrdersByUserId(final Integer userId) {
		final List<IOrderItem> orderItemList = orderItemService.getOderItemsByUserId(userId);

		// TODO: Replace set with list
		final Set<IOrder> orderSet = new HashSet<>();
		orderItemList.forEach(orderItem -> {
			final IOrder order = orderItem.getOrder();
			orderSet.add(order);
			order.addOrderItem(orderItem);
		});
		final List<IOrder> orderList = new ArrayList<>(orderSet);
		orderList.stream().sorted(Comparator.comparing(IOrder::getCreated));
		return orderList;
	}

	@Override public IOrder setStatus(final IOrder order, final OrderStatus status) {
		order.setStatus(status);
		orderDao.update(order);
		return order;
	}
}
