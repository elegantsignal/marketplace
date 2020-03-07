package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;


public interface IProductService {

	IProduct get(Integer id);

	List<IProduct> getAll();

	void save(IProduct entity);

	void delete(Integer id);

	void deleteAll();

	IProduct createEntity();

}
