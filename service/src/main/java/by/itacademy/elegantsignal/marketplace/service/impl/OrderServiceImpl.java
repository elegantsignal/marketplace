package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.Date;
import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.IOrderItemDao;
import by.itacademy.elegantsignal.marketplace.daoapi.IUserDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderFilter;
import by.itacademy.elegantsignal.marketplace.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.IOrderDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.service.IOrderService;

import javax.persistence.NoResultException;


@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderDao orderDao;

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IOrderItemService orderItemService;

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
		filter.setOrderStatus("CART");
		IOrder order;
		try {
			order = orderDao.findOne(filter);
		} catch (NoResultException e) {
			order = orderDao.createEntity();
			order.setUser(userDao.get(userId));
			order.setStatus("CART");
			save(order);
		}

		order.setOrderItem(orderItemService.getOrderItems(order));
		return order;
	}

	@Override public void setStatus(IOrder order, String status) {
		order.setStatus(status);
		orderDao.update(order);
	}
}
