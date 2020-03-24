package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;


public interface IProductService {

	IProduct get(Integer id);

	List<IProduct> getAll();

	@Transactional
	void save(IProduct entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	@Transactional
	IProduct createEntity();

	@Deprecated
	@Transactional
	void saveWithId(IProduct product);

	IProduct getFullInfo(Integer id);

}
