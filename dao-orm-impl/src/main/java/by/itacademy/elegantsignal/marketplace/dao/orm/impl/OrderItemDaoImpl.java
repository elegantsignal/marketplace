package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Order;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.OrderItem;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.OrderItem_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Order_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Product_;
import by.itacademy.elegantsignal.marketplace.daoapi.IOrderItemDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderItemFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


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

		// "Book" hardcoded but if we have multiple product types we need get this type from Product
		from.fetch(OrderItem_.product, JoinType.LEFT).fetch(Product_.book, JoinType.LEFT);
		// TODO: group with previous one
		from.fetch(OrderItem_.order, JoinType.LEFT);

		applyFilter(filter, criteriaBuilder, criteriaQuery, from);

		final TypedQuery<IOrderItem> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override
	public long getCount(final OrderItemFilter filter) {
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: getCount(); Timestamp: 3:32:28 PM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: getCount(); Timestamp: 3:32:28 PM");
		// return 0;
	}

	@Override
	public IOrderItem findOne(OrderItemFilter filter) {
		final EntityManager entityManager = getEntityManager();
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<IOrderItem> criteriaQuery = criteriaBuilder.createQuery(IOrderItem.class);
		final Root<OrderItem> from = criteriaQuery.from(OrderItem.class);
		criteriaQuery.select(from);

		from.fetch(OrderItem_.product, JoinType.LEFT);

		applyFilter(filter, criteriaBuilder, criteriaQuery, from);

		final TypedQuery<IOrderItem> query = entityManager.createQuery(criteriaQuery);
		return query.getSingleResult();
	}

	private void applyFilter(
		final OrderItemFilter filter,
		final CriteriaBuilder criteriaBuilder,
		final CriteriaQuery<IOrderItem> criteriaQuery,
		final Root<OrderItem> from) {

		final List<Predicate> ands = new ArrayList<>();

		if (filter.getId() != null) {
			ands.add(criteriaBuilder.equal(from.get(OrderItem_.id), filter.getId()));
		}

		if (filter.getUserId() != null) {
			ands.add(criteriaBuilder.equal(from.get(OrderItem_.order).get(Order_.user), filter.getUserId()));
		}

		if (!filter.getOrderIds().isEmpty()) {
			final List<Predicate> predicates = new ArrayList<>();
			filter.getOrderIds().forEach(orderId -> {
				predicates.add(criteriaBuilder.equal(from.get(OrderItem_.order), orderId));
			});
			ands.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
		}

		if (!filter.getExcludeOrderStatusList().isEmpty()) {
			final List<Predicate> predicates = new ArrayList<>();
			filter.getExcludeOrderStatusList().forEach(excludeStatus -> {
				predicates.add(criteriaBuilder.notEqual(from.get(OrderItem_.order).get(Order_.status), excludeStatus));
			});
			ands.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
		}

		if (!ands.isEmpty()) {
			criteriaQuery.where(criteriaBuilder.and(ands.toArray(new Predicate[0])));
		}
	}
}


