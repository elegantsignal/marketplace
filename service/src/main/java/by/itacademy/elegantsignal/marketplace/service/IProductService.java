package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;

import javax.transaction.Transactional;
import java.util.List;


public interface IProductService {

	@Transactional IProduct createEntity();

	@Transactional IProduct save(IProduct product);

	@Transactional void delete(Integer id);

	@Transactional void deleteAll();

	IProduct get(Integer id);

	List<IProduct> getAll();

	IProduct getFullInfo(Integer id);

	List<IProduct> getProductsByUserId(Integer userId);
}
