package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;

import javax.transaction.Transactional;
import java.util.List;


public interface IOrderItemService {

	@Transactional
	IOrderItem createEntity();

	IOrderItem get(Integer id);

	List<IOrderItem> getAll();

	List<IOrderItem> getOrderItems(IOrder order);

	List<IOrderItem> getOrderItems(List<IOrder> orders);

	@Transactional
	void save(IOrderItem entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	List<IOrderItem> getOderItemsByUserId(Integer userId);
}
