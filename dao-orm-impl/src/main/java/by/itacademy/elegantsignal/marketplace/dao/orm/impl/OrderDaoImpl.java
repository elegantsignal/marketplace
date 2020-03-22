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
		return null;
	}

	@Override
	public long getCount(final OrderFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IOrder getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
