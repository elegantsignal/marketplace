package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.Date;
import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.IProductDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daojdc.ProductDaoImpl;
import by.itacademy.elegantsignal.marketplace.service.IProductService;

public class ProductServiceImpl implements IProductService {

	private IProductDao dao = new ProductDaoImpl();

	@Override
	public IProduct createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IProduct entity) {
		final Date modifiedOn = new Date();
		entity.setUpdated(modifiedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifiedOn);
			dao.insert(entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public IProduct get(final Integer id) {
		final IProduct entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public List<IProduct> getAll() {
		final List<IProduct> all = dao.selectAll();
		return all;
	}

}
