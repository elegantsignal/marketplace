package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;


public interface IOrderService {

	IOrder get(Integer id);

	List<IOrder> getAll();

	@Transactional
	void save(IOrder entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	@Transactional
	IOrder createEntity();

	@Deprecated
	@Transactional
	void saveWithId(IOrder product);

	@Transactional
	IOrder getCartByUserId(Integer userId);

	@Transactional
	void setStatus(IOrder order, String status);
}
