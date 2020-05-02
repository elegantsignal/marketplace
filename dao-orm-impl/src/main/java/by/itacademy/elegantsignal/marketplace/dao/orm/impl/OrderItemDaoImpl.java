package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import java.util.ArrayList;
import java.util.List;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.*;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.daoapi.IOrderItemDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderItemFilter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;


@Repository
public class OrderItemDaoImpl extends AbstractDaoImpl<IOrderItem, Integer> implements IOrderItemDao {

	protected OrderItemDaoImpl() {
		super(OrderItem.class);
	}

	@Override
	public IOrderItem createEntity() {
		return new OrderItem();
	}

	@Override
	public List<IOrderItem> find(final OrderItemFilter filter) {
		final EntityManager entityManager = getEntityManager();
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<IOrderItem> criteriaQuery = criteriaBuilder.createQuery(IOrderItem.class);
		final Root<OrderItem> from = criteriaQuery.from(OrderItem.class);
		criteriaQuery.select(from);

		from.fetch(OrderItem_.product, JoinType.LEFT);


		applyFilter(filter, criteriaBuilder, criteriaQuery, from);

		final TypedQuery<IOrderItem> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	private void applyFilter(
			OrderItemFilter filter,
			CriteriaBuilder criteriaBuilder,
			CriteriaQuery<IOrderItem> criteriaQuery,
			Root<OrderItem> from) {

		final List<Predicate> ands = new ArrayList<>();

		final IOrder order = filter.getOrder();
		if (order != null) {
			ands.add(criteriaBuilder.equal(from.get(OrderItem_.order).get(Order_.id), order.getId()));
		}

		if (!ands.isEmpty()) {
			criteriaQuery.where(criteriaBuilder.and(ands.toArray(new Predicate[0])));
		}
	}

	@Override
	public long getCount(final OrderItemFilter filter) {
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: getCount(); Timestamp: 3:32:28 PM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: getCount(); Timestamp: 3:32:28 PM");
		// return 0;
	}

}
