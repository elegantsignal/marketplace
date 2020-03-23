package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.OrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.IOrderItemDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderItemFilter;


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
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: find(); Timestamp: 3:32:28 PM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: find(); Timestamp: 3:32:28 PM");
		// return null;
	}

	@Override
	public long getCount(final OrderItemFilter filter) {
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: getCount(); Timestamp: 3:32:28 PM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: getCount(); Timestamp: 3:32:28 PM");
		// return 0;
	}

}
