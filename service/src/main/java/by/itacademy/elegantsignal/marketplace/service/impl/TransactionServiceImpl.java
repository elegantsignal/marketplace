package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.ITransactionDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ITransaction;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.TransactionFilter;
import by.itacademy.elegantsignal.marketplace.service.ITransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Service @Slf4j
public class TransactionServiceImpl implements ITransactionService {

	@Autowired private ITransactionDao transactionDao;

	@Override public ITransaction createEntity() {
		return transactionDao.createEntity();
	}

	@Override public ITransaction save(final ITransaction transaction) {
		final Date modifiedOn = new Date();
		transaction.setUpdated(modifiedOn);

		if (transaction.getId() == null) {
			log.info("new transaction created: {}", transaction);
			transaction.setCreated(modifiedOn);
			transactionDao.insert(transaction);
		} else {
			log.info("user updated: {}", transaction);
			transactionDao.update(transaction);
		}

		return transaction;
	}

	@Override public ITransaction getById(final Integer id) {
		final TransactionFilter filter = new TransactionFilter().setId(id);
		return transactionDao.findOne(filter);
	}

	@Override public List<ITransaction> getTransactionByUser(final IUser user, final TransactionType type, final TransactionStatus status) {
		final TransactionFilter filter = new TransactionFilter()
			.setUserId(user.getId())
			.setType(type)
			.setStatus(status);
		return transactionDao.findAll(filter);
	}

	@Override public BigDecimal getTransactionSumByUser(final IUser user, final TransactionType type, final TransactionStatus status) {
		final TransactionFilter filter = new TransactionFilter()
			.setUserId(user.getId())
			.setType(type)
			.setStatus(status);
		final BigDecimal result = transactionDao.sumAmount(filter);
		if (result == null) {
			return BigDecimal.valueOf(0);
		}
		return result;
	}

}
