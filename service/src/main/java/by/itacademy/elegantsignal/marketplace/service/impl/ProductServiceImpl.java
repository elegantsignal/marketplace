package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.IProductDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.service.IProductService;


@Service
public class ProductServiceImpl implements IProductService {
	private IProductDao productDao;

	@Autowired
	public ProductServiceImpl(IProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public IProduct createEntity() {
		return productDao.createEntity();
	}

	@Override
	public void save(final IProduct entity) {
		final Date modifiedOn = new Date();
		entity.setUpdated(modifiedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifiedOn);
			productDao.insert(entity);
		} else {
			productDao.update(entity);
		}
	}

	@Override
	public IProduct get(final Integer id) {
		final IProduct entity = productDao.get(id);
		return entity;
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
		final List<IProduct> all = productDao.selectAll();
		return all;
	}

}
