package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Product;
import by.itacademy.elegantsignal.marketplace.daoapi.IProductDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.ProductFilter;


@Repository
public class ProductDaoImpl extends AbstractDaoImpl<IProduct, Integer> implements IProductDao {

	protected ProductDaoImpl() {
		super(Product.class);
	}

	@Override
	public IProduct createEntity() {
		return new Product();
	}

	@Override
	public List<IProduct> find(final ProductFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(final ProductFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}
