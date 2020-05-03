package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderFilter;

import javax.transaction.Transactional;
import java.util.List;


public interface IOrderService {

	IOrder get(Integer id);

	@Transactional
	IOrder getCartByUserId(Integer userId);

	@Transactional
	List<IOrder> getOrdersByUserId(Integer userId);

	List<IOrder> find(OrderFilter orderFilter);

	List<IOrder> getAll();

	@Transactional
	IOrder createEntity();

	@Transactional
	void save(IOrder entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	@Transactional
	void setStatus(IOrder order, OrderStatus status);

	@Deprecated
	@Transactional
	void saveWithId(IOrder product);
}
