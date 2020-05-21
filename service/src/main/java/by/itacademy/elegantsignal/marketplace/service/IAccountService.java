package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;

import java.math.BigDecimal;


public interface IAccountService {

	BigDecimal getTotalSalesAmount(IUser user);
}
