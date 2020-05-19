package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;

import java.util.List;


public interface ISalesService {

	List<IOrderItem> getSalesByUserId(Integer id);
}
