package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.IOrderDao;
import by.itacademy.elegantsignal.marketplace.daoapi.IOrderItemDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderFilter;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderItemFilter;
import by.itacademy.elegantsignal.marketplace.service.ICartService;
import by.itacademy.elegantsignal.marketplace.service.IOrderItemService;
import by.itacademy.elegantsignal.marketplace.service.IOrderService;
import by.itacademy.elegantsignal.marketplace.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;


@Service
public class CartServiceImpl implements ICartService {

	@Autowired private IUserService userService;
	@Autowired private IOrderService orderService;
	@Autowired private ICartService cartService;

	@Autowired private IOrderItemService orderItemService;
	@Autowired private IOrderDao orderDao;
	@Autowired private IOrderItemDao orderItemDao;

	@Override
	public IOrder getCartByUserId(final Integer userId) {
		final OrderFilter filter = new OrderFilter();
		filter.setUserId(userId).setOrderStatus(OrderStatus.CART);
		IOrder order;
		try {
			order = orderDao.findOne(filter);
		} catch (NoResultException e) {
			order = orderDao.createEntity();
			order.setUser(userService.get(userId));
			order.setStatus(OrderStatus.CART);
			orderService.save(order);
		}

		order.setOrderItemList(orderItemService.getOrderItems(order));
		return order;
	}

	@Override
	public void addToCart(final Integer userId, final IProduct product) {
		final IOrderItem orderItem = orderItemService.createEntity();
		orderItem.setOrder(cartService.getCartByUserId(userId));
		orderItem.setProduct(product);
		orderItem.setAmount(product.getPrice());
		orderItemService.save(orderItem);
	}

	@Override
	public void removeFromCart(final Integer userId, final IOrderItem orderItem) {
		final IOrder userCart = cartService.getCartByUserId(userId);
		final OrderItemFilter orderItemFilter = new OrderItemFilter();
		orderItemFilter.setId(orderItem.getId()).setOrderIds(userCart);
		final IOrderItem orderItemFromDb = orderItemDao.findOne(orderItemFilter);
		orderItemDao.delete(orderItemFromDb.getId());
	}

}
