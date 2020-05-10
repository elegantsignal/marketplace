package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;

import javax.transaction.Transactional;
import java.util.List;


public interface IOrderService {

	IOrder get(Integer id);

	@Transactional
	List<IOrder> getOrdersByUserId(Integer userId);

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

}
