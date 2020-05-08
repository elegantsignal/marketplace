package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;

import javax.transaction.Transactional;
import java.util.List;


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

	@Deprecated
	@Transactional
	void saveWithId(IOrderItem orderItem);

	List<IOrderItem> getOrderItems(IOrder order);

}
