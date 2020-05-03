package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Order;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Order_;
import by.itacademy.elegantsignal.marketplace.daoapi.IOrderDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderFilter;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class OrderDaoImpl extends AbstractDaoImpl<IOrder, Integer> implements IOrderDao {

	@PersistenceContext
	private EntityManager entityManager;

	protected OrderDaoImpl() {
		super(Order.class);
	}

	@Override
	public IOrder createEntity() {
		return new Order();
	}

	@Override public IOrder findOne(OrderFilter filter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<IOrder> criteriaQuery = criteriaBuilder.createQuery(IOrder.class);
		Root<Order> from = criteriaQuery.from(Order.class);
		criteriaQuery.select(from);

		from.fetch(Order_.user, JoinType.LEFT);

		applyFilter(filter, criteriaBuilder, criteriaQuery, from);

		TypedQuery<IOrder> query = entityManager.createQuery(criteriaQuery);
		return query.getSingleResult();
	}

	@Override
	public List<IOrder> find(final OrderFilter filter) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<IOrder> cq = criteriaBuilder.createQuery(IOrder.class);
		final Root<Order> from = cq.from(Order.class);
		cq.select(from);

		from.fetch(Order_.user, JoinType.LEFT);

		applyFilter(filter, criteriaBuilder, cq, from);

		// set sort params
		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, filter.getSortColumn());
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IOrder> q = entityManager.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(final Root<Order> from, final String sortColumn) {
		switch (sortColumn) {
			case "id":
				return from.get(Order_.id);
			case "created":
				return from.get(Order_.created);
			case "updated":
				return from.get(Order_.updated);
			default:
				throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(final OrderFilter filter) {
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: getCount(); Timestamp: 3:33:22 PM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: getCount(); Timestamp: 3:33:22 PM");
		// return 0;
	}

	@Override
	public IOrder getFullInfo(final Integer id) {
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: getFullInfo(); Timestamp: 3:33:22 PM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: getFullInfo(); Timestamp: 3:33:22 PM");
		// return null;
	}

	private void applyFilter(final OrderFilter filter,
		final CriteriaBuilder criteriaBuilder,
		final CriteriaQuery<?> criteriaQuery,
		final Root<Order> from) {

		final List<Predicate> ands = new ArrayList<>();

		final Integer userId = filter.getUserId();
		if (userId != null) {
			ands.add(criteriaBuilder.equal(from.get(Order_.user), userId));
		}

		List<OrderStatus> orderStatusList = filter.getOrderStatus();
		if (!orderStatusList.isEmpty()) {
			List<Predicate> predicates = new ArrayList<>();
			orderStatusList.forEach(orderStatus -> predicates.add(criteriaBuilder.equal(from.get(Order_.status), orderStatus)));
			ands.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
		}

		if (!ands.isEmpty()) {
			criteriaQuery.where(criteriaBuilder.and(ands.toArray(new Predicate[0])));
		}
	}

}
