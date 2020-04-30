package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import java.util.ArrayList;
import java.util.List;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.*;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Order;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.daoapi.IOrderDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;


@Repository
public class OrderDaoImpl extends AbstractDaoImpl<IOrder, Integer> implements IOrderDao {

	protected OrderDaoImpl() {
		super(Order.class);
	}

	@Override
	public IOrder createEntity() {
		return new Order();
	}

	@Override public IOrder findOne(OrderFilter filter) {
		EntityManager entityManager = getEntityManager();
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
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: find(); Timestamp: 3:33:22 PM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: find(); Timestamp: 3:33:22 PM");
		// return null;
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
		OrderStatus orderStatus = filter.getOrderStatus();

		if (!ands.isEmpty()) {
			criteriaQuery.where(criteriaBuilder.and(ands.toArray(new Predicate[0])));
		}
	}

}
