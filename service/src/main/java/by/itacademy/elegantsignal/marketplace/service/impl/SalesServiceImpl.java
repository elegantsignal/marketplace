package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.IOrderItemDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderItemFilter;
import by.itacademy.elegantsignal.marketplace.service.ISalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SalesServiceImpl implements ISalesService {

	@Autowired IOrderItemDao orderItemDao;

	@Override public List<IOrderItem> getSalesByUserId(final Integer userId) {
		final OrderItemFilter filter = new OrderItemFilter();
		filter.setProductOwnerId(userId);
		return orderItemDao.find(filter);
	}
}
