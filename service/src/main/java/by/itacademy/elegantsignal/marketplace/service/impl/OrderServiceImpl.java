package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.IOrderDao;
import by.itacademy.elegantsignal.marketplace.daoapi.IUserDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderFilter;
import by.itacademy.elegantsignal.marketplace.service.IOrderItemService;
import by.itacademy.elegantsignal.marketplace.service.IOrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired private IOrderDao orderDao;
	@Autowired private IUserDao userDao;
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

	@Override
	@Deprecated
	public void saveWithId(final IOrder order) {
		final Date modifiedOn = new Date();
		order.setUpdated(modifiedOn);
		order.setCreated(modifiedOn);
		orderDao.insert(order);
	}

	@Override
	public IOrder getCartByUserId(Integer userId) {
		OrderFilter filter = new OrderFilter();
		filter.setUserId(userId);
		filter.setOrderStatus(OrderStatus.CART);
		IOrder order;
		try {
			order = orderDao.findOne(filter);
		} catch (NoResultException e) {
			order = orderDao.createEntity();
			order.setUser(userDao.get(userId));
			order.setStatus(OrderStatus.CART);
			save(order);
		}

		order.setOrderItem(orderItemService.getOrderItems(order));
		return order;
	}

	@Override public List<IOrder> getOrdersByUserId(Integer userId) {
		OrderFilter orderFilter = new OrderFilter();
		orderFilter.setUserId(userId);
		List<OrderStatus> orderStatusList = new LinkedList<>(Arrays.asList(OrderStatus.values()));
			orderStatusList.removeIf(order -> order.equals(OrderStatus.CART));
		orderFilter.setOrderStatus(orderStatusList.toArray(new OrderStatus[0]));
		return orderDao.find(orderFilter);
	}

	@Override public List<IOrder> find(OrderFilter orderFilter) {
		return null;
	}

	@Override public void setStatus(IOrder order, OrderStatus status) {
		order.setStatus(status);
		orderDao.update(order);
	}
}
