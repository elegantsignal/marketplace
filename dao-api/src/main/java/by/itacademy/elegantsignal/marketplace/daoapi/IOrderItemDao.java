package by.itacademy.elegantsignal.marketplace.daoapi;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderItemFilter;


public interface IOrderItemDao extends IDao<IOrderItem, Integer> {

	List<IOrderItem> find(OrderItemFilter filter);

	long getCount(OrderItemFilter filter);
}
