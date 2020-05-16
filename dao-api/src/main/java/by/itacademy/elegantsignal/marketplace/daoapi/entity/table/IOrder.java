package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;

import java.util.Date;
import java.util.List;


public interface IOrder extends IBaseEntity {

	IUser getUser();

	IOrder setUser(IUser user);

	List<IOrderItem> getOrderItems();

	IOrder setOrderItemList(List<IOrderItem> orderItemList);

	Date getCreated();

	IOrder setCreated(Date created);

	Date getUpdated();

	IOrder setUpdated(Date update);

	IOrder setStatus(OrderStatus status);

	OrderStatus getStatus();

	IOrder addOrderItem(IOrderItem orderItem);
}
