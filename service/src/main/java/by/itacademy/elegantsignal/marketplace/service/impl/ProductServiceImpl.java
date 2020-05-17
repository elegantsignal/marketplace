package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.IProductDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.ProductFilter;
import by.itacademy.elegantsignal.marketplace.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao productDao;

	@Override public IProduct createEntity() {
		return productDao.createEntity();
	}

	@Override public IProduct save(final IProduct product) {
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

	@Override public IProduct get(final Integer id) {
		return productDao.get(id);
	}

	@Override public void delete(final Integer id) {
		productDao.delete(id);
	}

	@Override public void deleteAll() {
		productDao.deleteAll();
	}

	@Override public List<IProduct> getAll() {
		return productDao.selectAll();
	}

	@Override public IProduct getFullInfo(final Integer id) {
		return productDao.getFullInfo(id);
	}

	@Override public List<IProduct> getProductsByUserId(final Integer userId) {
		final ProductFilter filter = new ProductFilter().setUserId(userId);
		return productDao.find(filter);
	}

}
