package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ITransaction;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;


public interface ITransactionService {

	ITransaction createEntity();

	@Transactional ITransaction save(ITransaction transaction);

	ITransaction getById(Integer id);

	List<ITransaction> getTransactionByUserId(Integer userId, TransactionType type, TransactionStatus status);

	BigDecimal getTransactionSumByUserId(Integer userId, TransactionType type, TransactionStatus status);
}
