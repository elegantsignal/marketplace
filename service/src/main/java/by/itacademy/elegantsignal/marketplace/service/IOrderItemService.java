package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;


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

}
