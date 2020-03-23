package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Order;
import by.itacademy.elegantsignal.marketplace.daoapi.IOrderDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderFilter;


@Repository
public class OrderDaoImpl extends AbstractDaoImpl<IOrder, Integer> implements IOrderDao {

	protected OrderDaoImpl() {
		super(Order.class);
	}

	@Override
	public IOrder createEntity() {
		return new Order();
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

}
