package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.BaseEntity_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Product;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Product_;
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
	public IProduct getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IProduct> cq = cb.createQuery(IProduct.class); // define returning result
		final Root<Product> from = cq.from(Product.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Product_.user, JoinType.LEFT);
		// .. where id=...
		cq.where(cb.equal(from.get(BaseEntity_.id), id)); // where id=?

		final TypedQuery<IProduct> q = em.createQuery(cq);

		return q.getSingleResult();
	}

	@Override
	public List<IProduct> find(final ProductFilter filter) {
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: find(); Timestamp: 3:32:55 PM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: find(); Timestamp: 3:32:55 PM");
		// return null;
	}

	@Override
	public long getCount(final ProductFilter filter) {
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: getCount(); Timestamp: 3:32:55 PM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: getCount(); Timestamp: 3:32:55 PM");
		// return 0;
	}

}
