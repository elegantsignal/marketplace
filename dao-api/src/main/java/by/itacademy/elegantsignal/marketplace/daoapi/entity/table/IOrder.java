package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;

import java.util.Date;
import java.util.List;


public interface IOrder extends IBaseEntity {

	IUser getUser();

	IOrder setUser(IUser user);

	List<IOrderItem> getOrderItems();

	void setOrderItemList(List<IOrderItem> orderItemList);

	Date getCreated();

	void setCreated(Date created);

	Date getUpdated();

	void setUpdated(Date update);

	IOrder setStatus(OrderStatus status);

	OrderStatus getStatus();

	void addOrderItem(IOrderItem orderItem);
}
