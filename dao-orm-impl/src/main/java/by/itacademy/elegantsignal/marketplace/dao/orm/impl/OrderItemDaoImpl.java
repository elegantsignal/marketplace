package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.OrderItem;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.OrderItem_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Order_;
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

		Integer id = filter.getId();
		if (id != null) {
			ands.add(criteriaBuilder.equal(from.get(OrderItem_.id), id));
		}

		final Integer orderId = filter.getOrderId();
		if (orderId != null) {
			ands.add(criteriaBuilder.equal(from.get(OrderItem_.order).get(Order_.id), orderId));
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

}
