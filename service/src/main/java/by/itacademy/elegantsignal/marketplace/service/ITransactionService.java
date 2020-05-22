package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ITransaction;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;


public interface ITransactionService {

	ITransaction createEntity();

	@Transactional ITransaction save(ITransaction transaction);

	ITransaction getById(Integer id);

	List<ITransaction> getTransactionByUser(IUser user, TransactionType type, TransactionStatus status);

	BigDecimal getTransactionSumByUser(IUser user, TransactionType type, TransactionStatus status);
}
