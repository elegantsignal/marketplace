package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;


public interface IPaymentService {

	IOrder checkout(IOrder order);
}
