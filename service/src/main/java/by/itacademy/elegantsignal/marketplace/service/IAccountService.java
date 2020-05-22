package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;

import java.math.BigDecimal;


public interface IAccountService {

	BigDecimal getTotalSalesAmount(IUser user);

	BigDecimal getBalanceByUser(IUser user);

	void withdraw(IUser user, BigDecimal value);
}
