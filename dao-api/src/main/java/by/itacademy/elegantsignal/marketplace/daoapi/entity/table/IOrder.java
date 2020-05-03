package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;

import java.util.Date;
import java.util.List;


public interface IOrder extends IBaseEntity {

	IUser getUser();

	void setUser(IUser user);

	List<IOrderItem> getOrderItem();

	void setOrderItem(List<IOrderItem> orderItem);

	Date getCreated();

	void setCreated(Date created);

	Date getUpdated();

	void setUpdated(Date update);

	void setStatus(OrderStatus status);

	OrderStatus getStatus();
}
