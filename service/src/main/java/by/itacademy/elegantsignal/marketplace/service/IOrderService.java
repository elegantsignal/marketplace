package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;


public interface IOrderService {

	IOrder get(Integer id);

	List<IOrder> getAll();

	void save(IOrder entity);

	void delete(Integer id);

	void deleteAll();

	IOrder createEntity();

	@Deprecated
	void saveWithId(IOrder product);

}
