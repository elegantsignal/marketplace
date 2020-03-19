package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.math.BigDecimal;


public interface IOrderItem extends IBaseEntity {

	IOrder getOrder();

	void setOrder(IOrder order);

	IProduct getProduct();

	void setProduct(IProduct product);

	BigDecimal getAmount();

	void setAmount(BigDecimal amount);
}
