package by.itacademy.elegantsignal.marketplace.daoapi;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderFilter;


public interface IOrderDao extends IDao<IOrder, Integer> {

	List<IOrder> find(OrderFilter filter);

	long getCount(OrderFilter filter);

	IOrder getFullInfo(Integer id);
}
