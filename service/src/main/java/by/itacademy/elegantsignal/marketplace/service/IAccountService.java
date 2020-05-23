package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;

import java.math.BigDecimal;


public interface IAccountService {

	Account createEntity();

	BigDecimal getTotalSalesAmount(IUser user);

	BigDecimal getSpendableBalanceByUser(IUser user);

	void withdraw(IUser user, BigDecimal value);

	void withdraw(Account account);

	Account getAccountByUser(IUser user);
}
