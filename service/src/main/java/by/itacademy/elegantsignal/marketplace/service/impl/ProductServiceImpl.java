package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.IProductDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ProductServiceImpl implements IProductService {

	private final IProductDao productDao;

	@Autowired
	public ProductServiceImpl(final IProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public IProduct createEntity() {
		return productDao.createEntity();
	}

	@Override
	public IProduct save(final IProduct product) {
		final Date modifiedOn = new Date();
		product.setUpdated(modifiedOn);
		if (product.getId() == null) {
			product.setCreated(modifiedOn);
			productDao.insert(product);
		} else {
			productDao.update(product);
		}
		return product;
	}

	@Override
	public IProduct get(final Integer id) {
		return productDao.get(id);
	}

	@Override
	public void delete(final Integer id) {
		productDao.delete(id);
	}

	@Override
	public void deleteAll() {
		productDao.deleteAll();
	}

	@Override
	public List<IProduct> getAll() {
		return productDao.selectAll();
	}

	@Override
	@Deprecated
	public void saveWithId(final IProduct product) {
		final Date modifiedOn = new Date();
		product.setUpdated(modifiedOn);
		product.setCreated(modifiedOn);
		productDao.insert(product);
	}

	@Override
	public IProduct getFullInfo(Integer id) {
		return productDao.getFullInfo(id);
	}
}
