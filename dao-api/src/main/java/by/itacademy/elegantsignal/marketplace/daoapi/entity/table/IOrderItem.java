package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.math.BigDecimal;


public interface IOrderItem extends IBaseEntity {

	IOrder getOrder();

	IOrderItem setOrder(IOrder order);

	IProduct getProduct();

	IOrderItem setProduct(IProduct product);

	BigDecimal getAmount();

	void setAmount(BigDecimal amount);
}
