package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.IOrderItemDao;
import by.itacademy.elegantsignal.marketplace.daoapi.ITransactionDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ITransaction;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderItemFilter;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.TransactionFilter;
import by.itacademy.elegantsignal.marketplace.service.Account;
import by.itacademy.elegantsignal.marketplace.service.IAccountService;
import by.itacademy.elegantsignal.marketplace.service.ITransactionService;
import by.itacademy.elegantsignal.marketplace.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service @Slf4j
public class AccountServiceImpl implements IAccountService {

	@Autowired private IOrderItemDao orderItemDao;
	@Autowired private ITransactionService transactionService;
	@Autowired private IUserService userService;
	@Autowired private ITransactionDao transactionDao;

	@Override public Account createEntity() {
		return new Account();
	}

	@Override public BigDecimal getTotalSalesAmount(final IUser user) {
		final OrderItemFilter filter = new OrderItemFilter().setProductOwnerId(user.getId()).setOrderStarusList(OrderStatus.PAYED);
		final BigDecimal sum = orderItemDao.sumAmount(filter);
		if (sum == null) {
			return BigDecimal.valueOf(0);
		}
		return sum;
	}

	@Override public BigDecimal getSpendableBalanceByUser(final IUser user) {
		final TransactionFilter transactionFilter = new TransactionFilter()
			.setUserId(user.getId())
			.setType(TransactionType.WITHDRAWAL)
			.setExcludedStatus(TransactionStatus.FAILED);

		BigDecimal lockedTransactionsSum = transactionDao.sumAmount(transactionFilter);
		if (lockedTransactionsSum == null) {
			lockedTransactionsSum = BigDecimal.valueOf(0);
		}

		return getTotalSalesAmount(user).subtract(lockedTransactionsSum);
	}

	@Override public void withdraw(final IUser user, final BigDecimal value) {
		log.info("user {} request withdrawal of {}", user, value);
		final BigDecimal balance = getSpendableBalanceByUser(user);
		if (value.compareTo(balance) >= 0) {
			log.warn("User {} withdraw failed. Not enough money - balance: {}, amount: {}", user, balance, value);
			throw new IllegalArgumentException();
		}

		final ITransaction transaction = transactionService
			.createEntity()
			.setUser(user)
			.setAmount(value)
			.setType(TransactionType.WITHDRAWAL)
			.setStatus(TransactionStatus.PENDING);

		transactionService.save(transaction);
		// Do job - confirm transaction
		transaction.setStatus(TransactionStatus.SUCCESS);
		transactionService.save(transaction);
	}

	@Override public void withdraw(final Account account) {
		withdraw(account.getUser(), account.getWithdrawalAmount());
	}

	@Override public Account getAccountByUser(final IUser user) {
		return createEntity()
			.setUser(user)
			.setBalance(getSpendableBalanceByUser(user));

	}
}
