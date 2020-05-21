package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.IOrderItemDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderItemFilter;
import by.itacademy.elegantsignal.marketplace.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired private IOrderItemDao orderItemDao;

	@Override public BigDecimal getTotalSalesAmount(final IUser user) {
		final OrderItemFilter filter = new OrderItemFilter().setProductOwnerId(user.getId());
		return orderItemDao.sumAmount(filter);
	}
}
