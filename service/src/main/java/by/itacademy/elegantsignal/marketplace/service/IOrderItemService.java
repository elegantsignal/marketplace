package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;


public interface IOrderItemService {

	IOrderItem get(Integer id);

	List<IOrderItem> getAll();

	@Transactional
	void save(IOrderItem entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	@Transactional
	IOrderItem createEntity();

	@Transactional
	IOrderItem createEntity(IOrder order, IProduct product);

	@Deprecated
	@Transactional
	void saveWithId(IOrderItem orderItem);

	List<IOrderItem> getOrderItems(IOrder order);

}
