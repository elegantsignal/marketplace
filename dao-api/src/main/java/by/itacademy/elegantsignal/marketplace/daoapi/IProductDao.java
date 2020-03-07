package by.itacademy.elegantsignal.marketplace.daoapi;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.ProductFilter;


public interface IProductDao extends IDao<IProduct, Integer> {

	List<IProduct> find(ProductFilter filter);

	long getCount(ProductFilter filter);
}
