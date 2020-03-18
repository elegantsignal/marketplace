package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.IOrderDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.service.IOrderService;


@Service
public class OrderServiceImpl implements IOrderService {

	private final IOrderDao orderDao;

	@Autowired
	public OrderServiceImpl(final IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

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
		final IOrder entity = orderDao.get(id);
		return entity;
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
		final List<IOrder> all = orderDao.selectAll();
		return all;
	}

	@Override
	@Deprecated
	public void saveWithId(final IOrder order) {
		final Date modifiedOn = new Date();
		order.setUpdated(modifiedOn);
		order.setCreated(modifiedOn);
		orderDao.insert(order);
	}
}
