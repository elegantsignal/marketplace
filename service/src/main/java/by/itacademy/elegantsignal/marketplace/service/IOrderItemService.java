package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;


public interface IOrderItemService {

	IOrderItem get(Integer id);

	List<IOrderItem> getAll();

	void save(IOrderItem entity);

	void delete(Integer id);

	void deleteAll();

	IOrderItem createEntity();

	@Deprecated
	void saveWithId(IOrderItem orderItem);

}
