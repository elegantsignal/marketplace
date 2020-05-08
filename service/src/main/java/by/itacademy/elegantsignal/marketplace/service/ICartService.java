package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;

import javax.transaction.Transactional;


public interface ICartService {

	IOrder getCartByUserId(Integer userId);

	@Transactional
	void addToCart(Integer userId, IProduct product);

	@Transactional
	void removeFromCart(Integer userId, IOrderItem orderItem);
}
