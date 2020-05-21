package by.itacademy.elegantsignal.marketplace.daoapi;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ITransaction;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.TransactionFilter;

import java.math.BigDecimal;
import java.util.List;


public interface ITransactionDao extends IDao<ITransaction, Integer> {

	ITransaction findOne(TransactionFilter filter);

	List<ITransaction> findAll(TransactionFilter filter);

	BigDecimal sumAmount(TransactionFilter filter);
}
