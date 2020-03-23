package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.IOrderItemDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.service.IOrderItemService;


@Service
public class OrderItemServiceImpl implements IOrderItemService {

	private final IOrderItemDao orderItemDao;

	@Autowired
	public OrderItemServiceImpl(final IOrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}

	@Override
	public IOrderItem createEntity() {
		return orderItemDao.createEntity();
	}

	@Override
	public void save(final IOrderItem entity) {
		if (entity.getId() == null) {
			orderItemDao.insert(entity);
		} else {
			orderItemDao.update(entity);
		}
	}

	@Override
	public IOrderItem get(final Integer id) {
		return orderItemDao.get(id);
	}

	@Override
	public void delete(final Integer id) {
		orderItemDao.delete(id);
	}

	@Override
	public void deleteAll() {
		orderItemDao.deleteAll();
	}

	@Override
	public List<IOrderItem> getAll() {
		return orderItemDao.selectAll();
	}

	@Override
	@Deprecated
	public void saveWithId(final IOrderItem orderItem) {
		orderItemDao.insert(orderItem);
	}
}
